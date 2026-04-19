package com.msd.atlantis_fest.repository;

import com.msd.atlantis_fest.entity.Foodtruck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodtruckRepository extends JpaRepository<Foodtruck, Long> {
}
