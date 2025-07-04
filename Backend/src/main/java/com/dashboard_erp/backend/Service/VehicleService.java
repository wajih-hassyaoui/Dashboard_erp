package com.dashboard_erp.backend.Service;

import com.dashboard_erp.backend.DTO.VehicleDto;
import com.dashboard_erp.backend.DTO.VehicleResponseDto;
import com.dashboard_erp.backend.Entity.Vehicle;
import com.dashboard_erp.backend.Mapper.VehicleMapper;
import com.dashboard_erp.backend.Repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper;
    public VehicleService(VehicleRepository vehicleRepository, VehicleMapper vehicleMapper) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleMapper = vehicleMapper;
    }
    public List<Vehicle> getAllVehicles() {
        if (vehicleRepository.findAll().isEmpty()) {
            throw new RuntimeException("No vehicles found");
        }
        return vehicleRepository.findAll();

    }
    public Vehicle getVehicleById(int id) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);
        if (vehicle.isPresent()) {
            return vehicle.get();
        }
        throw new RuntimeException("No vehicle found");
    }
    public VehicleResponseDto saveVehicle(VehicleDto vehicleDto) {
        if(vehicleRepository.existsByLicencePlate(vehicleDto.licencePlate())) {
            throw new RuntimeException("Licence plate already exists");
        }
        var vehicle = vehicleMapper.vehicleToVehicleDto(vehicleDto);
        var saveVehicle= vehicleRepository.save(vehicle);

        return vehicleMapper.getVehicleDto(saveVehicle);
    }
    public Vehicle updateVehicle(Integer id,Vehicle vehicle) {
        Vehicle oldVehicle = vehicleRepository.findById(id).orElseThrow(() -> new RuntimeException("No vehicle found"));
        if (vehicle.getModel()!=null)
            oldVehicle.setModel(vehicle.getModel());
        if (vehicle.getLicencePlate()!=null)
            oldVehicle.setLicencePlate(vehicle.getLicencePlate());
        if (vehicle.getMake()!=null)
            oldVehicle.setMake(vehicle.getMake());
        return vehicleRepository.save(oldVehicle);
    }
    public void deleteVehicleById(int id) {
         vehicleRepository.deleteById(id);
    }


}
