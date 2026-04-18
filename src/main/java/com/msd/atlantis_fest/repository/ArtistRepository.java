package com.msd.atlantis_fest.repository;

import com.msd.atlantis_fest.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
}