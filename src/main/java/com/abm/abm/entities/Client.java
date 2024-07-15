package com.abm.abm.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer docnumber;

    @OneToMany(mappedBy = "client")
    @JsonBackReference
    private List<Cart> carts;

    @OneToMany(mappedBy = "client")
    @JsonBackReference
    private List<Invoice> invoices;
}
