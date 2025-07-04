package com.dashboard_erp.backend.DTO;


import jakarta.validation.constraints.NotEmpty;

public record VehicleDto(
        @NotEmpty(message = "champ make should be not empty")
        String make,
        @NotEmpty(message = "champ model should be not empty" )
        String model,
        @NotEmpty(message = "champ licence Plate should be not empty")
        String licencePlate,
        Integer userId

) {
}
