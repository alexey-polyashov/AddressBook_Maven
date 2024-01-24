package com.example.adrbook.repo;

import com.example.adrbook.entity.ServiceInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceInfoRepo extends JpaRepository<ServiceInfo, Long> {
    ServiceInfo getFirstByOrderById();
}
