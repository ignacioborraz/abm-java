package com.abm.abm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abm.abm.entities.Client;

@Repository
public interface ClientsRepository extends JpaRepository<Client, Long> {
}
