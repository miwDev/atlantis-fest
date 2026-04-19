package com.msd.atlantis_fest.repository;

import com.msd.atlantis_fest.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
}