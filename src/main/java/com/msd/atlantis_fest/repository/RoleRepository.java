package com.msd.atlantis_fest.repository;

import com.msd.atlantis_fest.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}