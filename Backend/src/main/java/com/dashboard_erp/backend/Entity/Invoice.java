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
public class Invoice {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDate invoiceDate;
    private Double totalAmount;
    private StatusInvoice status;
    @OneToOne(mappedBy = "invoice", cascade = CascadeType.ALL)
    private Payment payment;

}
