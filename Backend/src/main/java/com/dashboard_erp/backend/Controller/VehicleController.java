package com.dashboard_erp.backend.Controller;

import com.dashboard_erp.backend.DTO.VehicleDto;
import com.dashboard_erp.backend.Entity.Vehicle;
import com.dashboard_erp.backend.Service.VehicleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/vehicle")
public class VehicleController {
    private final VehicleService vehicleService;
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getAllVehicles() {
        try{
            List<Vehicle> vehicles=vehicleService.getAllVehicles();
            return ResponseEntity.status(HttpStatus.OK).body(vehicles);
        }
        catch (RuntimeException e){
            if (ResponseEntity.status(HttpStatus.CONFLICT).equals(HttpStatus.CONFLICT)){
                return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getVehicle(@PathVariable("id") Integer id) {
        try{
            Vehicle vehicle=vehicleService.getVehicleById(id);
            return ResponseEntity.status(HttpStatus.OK).body(vehicle);
        }
        catch (RuntimeException e){
            if (ResponseEntity.status(HttpStatus.CONFLICT).equals(HttpStatus.CONFLICT)){
                return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addVehicle(@Valid @RequestBody VehicleDto vehicleDto) {
        try{
            var newVehicle=vehicleService.saveVehicle(vehicleDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(newVehicle);
        }
        catch (RuntimeException e){
            if (e.getMessage().contains("Licence plate already exists")) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> updateVehicle(@PathVariable("id") Integer id, @RequestBody Vehicle vehicle) {
       try {
           Vehicle updatedVehicle = vehicleService.updateVehicle(id, vehicle);
           return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedVehicle);
       }catch (RuntimeException e){
           if (ResponseEntity.status(HttpStatus.CONFLICT).equals(HttpStatus.CONFLICT)){
               return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
           }
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
       }
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> deleteVehicle(@PathVariable("id") Integer id) {
        try{
            vehicleService.deleteVehicleById(id);
            return ResponseEntity.ok("vehicle deleted with success");

        }catch (RuntimeException e){
            if (ResponseEntity.status(HttpStatus.CONFLICT).equals(HttpStatus.CONFLICT)){
                return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
