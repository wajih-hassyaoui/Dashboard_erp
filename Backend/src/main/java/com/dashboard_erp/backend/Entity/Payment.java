package com.dashboard_erp.backend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {
    @Id
    @GeneratedValue
    private Integer id;
    private int amount;
    private LocalDate paymentDate;
    @OneToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;
}
