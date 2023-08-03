package com.example.demo.appuser;

import com.example.demo.registration.token.ConfirmationToken;
import com.example.demo.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";
    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(username).
                orElseThrow(()->new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, username)));
    }

    public String signUpUser(AppUser appUser){
        Optional<AppUser> userByEmail = appUserRepository.findByEmail(appUser.getEmail());
        boolean userExists = userByEmail.isPresent();

        // 用户存在
        if (userExists){
            // 获取用户
            AppUser user = userByEmail.get();
            // 看是否已经激活，若激活则抛出异常
            if (user.getEnabled()){
                 throw new IllegalStateException("email has already been activated");
            }
            /*
             若用户存在且未激活，说明用户注册账户时：
              1.激活过期，那么更新并覆盖该账户注册信息;
              2.激活未过期，那么抛出异常
            */
            else {
                // 只取最新的一条Token数据
                ConfirmationToken latestToken = getLatestToken(user);
                // 如果不过期，抛异常，提示激活
                if (LocalDateTime.now().isBefore(latestToken.getExpiresAt())){
                    throw new IllegalStateException("link is still valid, check your email and activate it");
                }
                // 这里要使用新数据update user是老数据
                appUserRepository.updateAppUserByEmail(
                        appUser.getEmail(),
                        appUser.getFirstName(),
                        appUser.getLastName(),
                        bCryptPasswordEncoder.encode(appUser.getPassword())
                );
                // 补充appUser id
                appUser.setId(user.getId());

                return generateTokenAndSave(appUser);
            }
        }

        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodedPassword);

        appUserRepository.save(appUser);

        return generateTokenAndSave(appUser);
    }

    private ConfirmationToken getLatestToken(AppUser user) {
        // 查询这个用id注册过的所有token
        List<ConfirmationToken> allTokens = confirmationTokenService.getAllTokens(user.getId());
        Optional<ConfirmationToken> latestToken = allTokens.stream().max(Comparator.comparing(ConfirmationToken::getCreatedAt));
        List<ConfirmationToken> oneToken = new ArrayList<>();
        latestToken.ifPresent(oneToken::add);
        return oneToken.get(0);
    }

    private String generateTokenAndSave(AppUser appUser) {
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser
        );
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        return token;
    }

    public void enableAppUser(String email) {
        appUserRepository.activeAccount(email);
    }
}
