package com.msd.atlantis_fest.repository;

import com.msd.atlantis_fest.entity.Concert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConcertRepository extends JpaRepository<Concert, Long> {
    Page<Concert> findByArtistId(Long artistId, Pageable pageable);
}
