package com.msd.atlantis_fest.repository;

import com.msd.atlantis_fest.entity.Zone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZoneRepository extends JpaRepository<Zone, Long> {
    Page<Zone> findByFestivalId(Long festivalId, Pageable pageable);
}
