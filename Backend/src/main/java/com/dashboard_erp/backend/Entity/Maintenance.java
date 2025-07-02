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
public class Maintenance {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(nullable = false)
    @NotBlank(message = "name is required")
    private String name;
    private String description;
    private String price;
    @ManyToMany(mappedBy = "services")
    private List<Appointment> appointments;
}
