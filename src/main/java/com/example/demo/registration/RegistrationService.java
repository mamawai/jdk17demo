package com.example.demo.registration;

import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRole;
import com.example.demo.appuser.AppUserService;
import com.example.demo.email.EmailSender;
import com.example.demo.registration.token.ConfirmationToken;
import com.example.demo.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final AppUserService appUserService;
    private final EmailValidator emailValidator;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailSender emailSender;

    @Transactional
    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if (!isValidEmail){
            throw new IllegalStateException("email not valid");
        }
        String token = appUserService.signUpUser(
                new AppUser(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPassword(),
                        AppUserRole.USER
                )
        );
        String link = "http://localhost:8080/api/v1/registration/confirm?token=" + token;
        emailSender.send(request.getEmail(),buildEmail(request.getFirstName(),link));
        return token;
    }

    @Transactional
    public String confirmToken(String token){
        ConfirmationToken confirmationToken = confirmationTokenService.getToken(token).orElseThrow(() -> new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null){
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiresAt = confirmationToken.getExpiresAt();

        if (expiresAt.isBefore(LocalDateTime.now())){
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        appUserService.enableAppUser(confirmationToken.getAppUser().getEmail());

        return "confirmed";
    }

    private String buildEmail(String firstName,String link) {
        return "<!DOCTYPE html>\n" +
               "<html>\n" +
               "<head>\n" +
               "    <title>你好!</title>\n" +
               "</head>\n" +
               "<body>\n" +
               "    <h1>欢迎"+firstName+"注册我们的网站</h1>\n" +
               "    <p>点击以下链接以激活用户权限：</p>\n" +
               "    <p><a href="+link+">点击激活账户</a></p>\n" +
               "    <p>感谢您的支持！</p>\n" +
               "</body>\n" +
               "</html>";
    }

}
