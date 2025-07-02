package com.dashboard_erp.backend.Service;

import com.dashboard_erp.backend.DTO.MaintenanceStatusDTO;
import com.dashboard_erp.backend.Entity.Maintenance;
import com.dashboard_erp.backend.Entity.MaintenanceStatus;
import com.dashboard_erp.backend.Repository.MaintenanceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaintenanceSevice {
    private final MaintenanceRepository maintenanceRepository;
    public MaintenanceSevice(MaintenanceRepository maintenanceRepository) {
        this.maintenanceRepository = maintenanceRepository;
    }
    public Maintenance addMaintenance(Maintenance maintenance) {
        if(maintenanceRepository.existsByNameIgnoreCase(maintenance.getName())) {
            throw  new RuntimeException("maintenance already exists");
        }
        return maintenanceRepository.save(maintenance);

    }
    public List<Maintenance> getAllMaintenance()  {
        if(maintenanceRepository.findAll().isEmpty()) {
            throw  new RuntimeException("maintenance not exists");
        }
        return maintenanceRepository.findAll();
    }
    public Maintenance getMaintenance(int id) {
       return maintenanceRepository.findById(id).orElseThrow(()-> new RuntimeException("maintenance not exists"));
    }
    public Maintenance updateMaintenance(Integer id,Maintenance maintenance) {
        Maintenance oldMaintenance = maintenanceRepository.findById(id).orElseThrow(()-> new RuntimeException("maintenance not exists"));
        if(maintenance.getName()!=null) {
            oldMaintenance.setName(maintenance.getName());
        }
        if(maintenance.getDescription()!=null) {
            oldMaintenance.setDescription(maintenance.getDescription());
        }
        if (maintenance.getPrice()!=null) {
            oldMaintenance.setPrice(maintenance.getPrice());
        }

        return maintenanceRepository.save(oldMaintenance);
    }
    public void deleteMaintenance(int id) {
        if (maintenanceRepository.existsById(id)==false) {
            throw  new RuntimeException("maintenance not exists");
        }
        maintenanceRepository.deleteById(id);
    }
    public void changeMaintenanceStatus(Integer id, MaintenanceStatusDTO maintenance) {
        Maintenance oldmaintenance=maintenanceRepository.findById(id).orElseThrow(()-> new RuntimeException("maintenance not exists"));
        oldmaintenance.setStatus(maintenance.getStatus());

        maintenanceRepository.save(oldmaintenance);

    }


}
