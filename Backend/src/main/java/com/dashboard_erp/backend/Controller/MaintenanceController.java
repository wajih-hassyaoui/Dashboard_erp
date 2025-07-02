package com.dashboard_erp.backend.Controller;

import com.dashboard_erp.backend.DTO.MaintenanceStatusDTO;
import com.dashboard_erp.backend.Entity.Maintenance;
import com.dashboard_erp.backend.Entity.MaintenanceStatus;
import com.dashboard_erp.backend.Service.MaintenanceSevice;
import jakarta.validation.Valid;
import org.hibernate.annotations.Fetch;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/maintenance")
public class MaintenanceController {
    private final MaintenanceSevice maintenanceSevice;
    public MaintenanceController(MaintenanceSevice maintenanceSevice) {
        this.maintenanceSevice = maintenanceSevice;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> maintenance(@Valid @RequestBody Maintenance maintenance) {
        try{
            maintenanceSevice.addMaintenance(maintenance);
            return ResponseEntity.status(HttpStatus.CREATED).body(maintenance);
        }catch (RuntimeException e){
            if(ResponseEntity.status(HttpStatus.CONFLICT).equals(HttpStatus.CONFLICT))
                return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getAllMaintenance() {
        try {
            List<Maintenance> maintenances = maintenanceSevice.getAllMaintenance();
            return ResponseEntity.status(HttpStatus.OK).body(maintenances);
        } catch (RuntimeException e) {
            if(ResponseEntity.status(HttpStatus.CONFLICT).equals(HttpStatus.CONFLICT))
                return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> updateMaintenance(@PathVariable("id") Integer id, @Valid @RequestBody Maintenance maintenance) {
        try{
            maintenanceSevice.updateMaintenance(id,maintenance);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("maintenance updated with success");
        }catch(RuntimeException e){
            if (ResponseEntity.status(HttpStatus.CONFLICT).equals(HttpStatus.CONFLICT))
                return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> deleteMaintenance(@PathVariable("id") int id) {
        try {
            maintenanceSevice.deleteMaintenance(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("maintenance deleted with success");
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }
    @PutMapping("/status/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> updateMaintenanceStatus(@PathVariable("id") int id, @Valid @RequestBody MaintenanceStatusDTO statusDTO) {
        try{
            maintenanceSevice.changeMaintenanceStatus(id, statusDTO);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("maintenance status changed with success to :"+statusDTO.getStatus());
        }catch (RuntimeException e){
            if(ResponseEntity.status(HttpStatus.CONFLICT).equals(HttpStatus.CONFLICT))
                return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }



}
