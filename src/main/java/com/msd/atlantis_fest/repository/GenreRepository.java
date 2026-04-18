package com.msd.atlantis_fest.repository;

import com.msd.atlantis_fest.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}