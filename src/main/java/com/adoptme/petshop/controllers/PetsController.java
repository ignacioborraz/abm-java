package com.adoptme.petshop.controllers;

import com.adoptme.petshop.entities.Pet;
import com.adoptme.petshop.services.PetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/pets")
public class PetsController {

    @Autowired private PetsService service;

    @PostMapping
    public ResponseEntity<Pet> create(@RequestBody Pet pet) {
        try {
            Pet newUser = service.save(pet);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        } catch(Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Pet>> readAll() {
        try {
            List<Pet> users = service.readAll();
            return ResponseEntity.ok(users);
        } catch(Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pet> read(@PathVariable Long id) {
        try {
            Optional<Pet> pet = service.readOne(id);
            if (pet.isPresent()) {
                return ResponseEntity.ok(pet.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch(Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pet> update(@PathVariable Long id, @RequestBody Pet data) {
        try {
            Optional<Pet> optionalPet = service.readOne(id);
            if (optionalPet.isPresent()) {
                Pet pet = optionalPet.get();
                if (data.getName() != null) {
                    pet.setName(data.getName());
                }
                if (data.getSpecie() != null) {
                    pet.setSpecie(data.getSpecie());
                }
                service.save(pet);
                return ResponseEntity.ok(pet);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch(Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Pet> destroy(@PathVariable Long id) {
        try {
            Optional<Pet> pet = service.destroyOne(id);
            if (pet.isPresent()) {
                return ResponseEntity.ok(pet.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch(Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{pid}/adopt/{uid}")
    public ResponseEntity<Pet> adopt(@PathVariable Long pid, @PathVariable Long uid) {
        try {
            Optional<Pet> pet = service.adopt(pid,uid);
            if (pet.isPresent()) {
                return ResponseEntity.ok(pet.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch(Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}