package com.msd.atlantis_fest.repository;

import com.msd.atlantis_fest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}