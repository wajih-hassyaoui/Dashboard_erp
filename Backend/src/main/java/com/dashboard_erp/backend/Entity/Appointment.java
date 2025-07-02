package com.dashboard_erp.backend.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Appointment {
    @Id
    @GeneratedValue
    private Integer id;
    private String amount;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    @NotBlank(message = "appointment is required")
    private LocalDate appointmentDate;
    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;
    @ManyToMany
    @JoinTable(
            name = "Appointment_Services",
            joinColumns = @JoinColumn(name = "appointment_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id")
    )
    private List<Maintenance> services;

}
