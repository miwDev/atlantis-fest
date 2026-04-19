package com.msd.atlantis_fest.repository;

import com.msd.atlantis_fest.entity.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZoneRepository extends JpaRepository<Zone, Long> {
    List<Zone> findByFestivalId(Long festivalId);
}
