package com.msd.atlantis_fest.repository;

import com.msd.atlantis_fest.entity.Festival;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FestivalRepository extends JpaRepository<Festival, Long> {
}