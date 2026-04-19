package com.msd.atlantis_fest.repository;

import com.msd.atlantis_fest.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}