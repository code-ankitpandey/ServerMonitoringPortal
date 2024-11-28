package com.moniotring.portal.DTO;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class UserDTO {
    @Id
            String userName;
            String firstName;
            String lastName;
            String email;
            String password;
            boolean isActive;
            @Enumerated(EnumType.STRING)
            private Role role;
}