package com.moniotring.portal.Entity;

import com.moniotring.portal.DTO.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class User {
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
