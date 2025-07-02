package com.dashboard_erp.backend.DTO;


import com.dashboard_erp.backend.Entity.MaintenanceStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MaintenanceStatusDTO {
    @NotNull
    private MaintenanceStatus status;
}
