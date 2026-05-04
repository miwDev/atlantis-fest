package com.msd.atlantis_fest.repository;

import com.msd.atlantis_fest.entity.Purchase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    
    long countByTicketTypeId(Long ticketTypeId);

    @Query("SELECT SUM(p.precioFinal) FROM Purchase p WHERE p.ticketType.id = :ticketTypeId")
    Double sumPrecioFinalByTicketTypeId(@Param("ticketTypeId") Long ticketTypeId);

    Page<Purchase> findByClientId(Long clientId, Pageable pageable);
}