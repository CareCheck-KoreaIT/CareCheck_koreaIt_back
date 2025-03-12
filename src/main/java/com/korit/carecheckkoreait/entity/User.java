package com.korit.carecheckkoreait.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int userId;
    private String password;
    private String username;
    private String email;
    private String phoneNumber;
    private int accountExpired;
    private int accountLocked;
    private int credentialsExpired;
    private int accountEnabled;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private UserRole userRole;

}
