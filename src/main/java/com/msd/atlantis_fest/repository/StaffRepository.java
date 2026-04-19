package com.msd.atlantis_fest.repository;

import com.msd.atlantis_fest.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff, Long> {
}