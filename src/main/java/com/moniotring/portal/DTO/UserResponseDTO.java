package com.moniotring.portal.DTO;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class UserResponseDTO {
    @Id
    String userName;
    String firstName;
    String lastName;
    String email;
    boolean isActive;
    @Enumerated(EnumType.STRING)
    private Role role;
}

