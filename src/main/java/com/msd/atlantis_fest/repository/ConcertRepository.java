package com.msd.atlantis_fest.repository;

import com.msd.atlantis_fest.entity.Concert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcertRepository extends JpaRepository<Concert, Long> {
}