package com.dashboard_erp.backend.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(nullable = false)
    @NotBlank(message = "First name is required")
    private String firstName;
    @Column(nullable = false)
    @NotBlank(message = "Last name")
    private String lastName;
    @Email(message = "Email should be valid")
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    @NotBlank(message = "phone number is required")
    private String phone;
    @Column(nullable = false)
    @NotBlank(message = "address is required")
    private String address;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private Role role;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Vehicle> vehicles;

//    public Integer getId() {
//        return id;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public String getPhone() {
//        return phone;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public List<Vehicle> getVehicles() {
//        return vehicles;
//    }
}
