package com.dashboard_erp.backend.Repository;

import com.dashboard_erp.backend.Entity.User;
import com.dashboard_erp.backend.Entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
    @Query("select count(*) from Vehicle  as v where v.user=:user")
    public int countVehicles(User user);
    public boolean existsByLicencePlate(String licencePlate);

}
