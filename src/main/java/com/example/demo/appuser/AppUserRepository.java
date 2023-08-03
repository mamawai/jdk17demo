package com.example.demo.appuser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Repository
@Transactional(readOnly = true)
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByEmail(String email);

    @Modifying
    @Query("update AppUser as u set u.enabled = true where u.email =?1 ")
    void activeAccount(String email);

    @Modifying
    @Query("update AppUser as u set u.firstName = ?2, u.lastName = ?3, u.password = ?4 where u.email =?1 ")
    void updateAppUserByEmail(String email, String firstName, String lastName, String password);
}
