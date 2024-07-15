package com.abm.abm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abm.abm.entities.Invoice;

@Repository
public interface InvoicesRepository extends JpaRepository<Invoice, Long> {
}
