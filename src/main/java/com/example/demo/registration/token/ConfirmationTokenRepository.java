package com.example.demo.registration.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken,Long> {

    Optional<ConfirmationToken> findByToken(String token);

    List<ConfirmationToken> findAllByAppUser_Id(Long appUserId);

    @Modifying
    @Query("update ConfirmationToken as u set u.confirmedAt =?1 where u.token =?2 ")
    void setConfirmedAtByToken(LocalDateTime confirmedAt, String token);

}
