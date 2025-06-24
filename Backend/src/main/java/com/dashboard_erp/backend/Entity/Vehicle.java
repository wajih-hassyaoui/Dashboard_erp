package com.dashboard_erp.backend.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicle {
    @Id
    @GeneratedValue
    private Integer id;
    private String make;
    private String model;
    @NotBlank(message = "licence plate is required")
    @Column(unique = true, nullable = false)
    private String licencePlate;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    private List<Appointment> appointments;



}
