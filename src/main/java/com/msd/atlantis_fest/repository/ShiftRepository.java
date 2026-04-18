package com.msd.atlantis_fest.repository;

import com.msd.atlantis_fest.entity.Shift;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShiftRepository extends JpaRepository<Shift, Long> {
}