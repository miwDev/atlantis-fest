package com.msd.atlantis_fest.repository;

import com.msd.atlantis_fest.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}