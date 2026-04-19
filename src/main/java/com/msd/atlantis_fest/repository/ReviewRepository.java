package com.msd.atlantis_fest.repository;

import com.msd.atlantis_fest.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}