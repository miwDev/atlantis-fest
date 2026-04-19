package com.msd.atlantis_fest.repository;

import com.msd.atlantis_fest.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}