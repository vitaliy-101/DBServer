package com.example.datamanagementserver.dto.user;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String email;
    private String firstname;
    private String surname;
    private String patronymic;
    private String password;
    private Long groupId;
}
