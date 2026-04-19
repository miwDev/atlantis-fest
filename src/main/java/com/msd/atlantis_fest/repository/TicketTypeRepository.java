package com.msd.atlantis_fest.repository;

import com.msd.atlantis_fest.entity.TicketType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketTypeRepository extends JpaRepository<TicketType, Long> {
}