package com.dashboard_erp.backend.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(nullable = false)
    @NotBlank(message = "User name is required")
    private String userName;
    @Column(nullable = false)
    @NotBlank(message = "Phone number is required")
    private int phoneNumber;
    @Column(nullable = false, unique = true)
    @Email(message = "Email should be valid")
    private String email;
    @Column(nullable = false)
    private String password;

}
