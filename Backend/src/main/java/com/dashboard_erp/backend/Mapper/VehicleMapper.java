package com.dashboard_erp.backend.Mapper;

import com.dashboard_erp.backend.DTO.VehicleDto;
import com.dashboard_erp.backend.DTO.VehicleResponseDto;
import com.dashboard_erp.backend.Entity.User;
import com.dashboard_erp.backend.Entity.Vehicle;
import org.springframework.stereotype.Service;

@Service
public class VehicleMapper {
    public Vehicle vehicleToVehicleDto(VehicleDto vehicleDto) {
        if (vehicleDto == null) {
            throw new NullPointerException("vehicleDto cannot be null");
        }
        Vehicle vehicle = new Vehicle();
        vehicle.setMake(vehicleDto.make());
        vehicle.setModel(vehicleDto.model());
        vehicle.setLicencePlate(vehicleDto.licencePlate());
        User user = new User();
        user.setId(vehicleDto.userId());
        vehicle.setUser(user);
        return vehicle;
    }
    public VehicleResponseDto getVehicleDto(Vehicle vehicle) {
        return new VehicleResponseDto(vehicle.getMake(), vehicle.getModel(), vehicle.getLicencePlate());
    }
}
