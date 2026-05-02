package com.msd.atlantis_fest.repository;

import com.msd.atlantis_fest.entity.TicketType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketTypeRepository extends JpaRepository<TicketType, Long> {
    Page<TicketType> findByFestivalId(Long festivalId, Pageable pageable);
}