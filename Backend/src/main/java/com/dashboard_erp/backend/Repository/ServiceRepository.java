package com.dashboard_erp.backend.Repository;

import com.dashboard_erp.backend.Entity.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Maintenance, Integer> {
}
