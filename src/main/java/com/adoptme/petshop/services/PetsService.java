package com.adoptme.petshop.services;

import com.adoptme.petshop.entities.Pet;
import com.adoptme.petshop.entities.User;
import com.adoptme.petshop.repositories.PetsRepository;
import com.adoptme.petshop.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetsService {

    @Autowired private PetsRepository repository;
    @Autowired private UsersRepository usersRepository;

    public Pet save(Pet pet) {
        return repository.save(pet);
    }

    public List<Pet> readAll() {
        return repository.findAll();
    }

    public Optional<Pet> readOne(Long id) {
        return repository.findById(id);
    }

    public Optional<Pet> destroyOne(Long id) {
        Optional<Pet> pet = repository.findById(id);
        if (pet.isPresent()) {
            repository.deleteById(id);
        }
        return pet;
    }

    public Optional<Pet> adopt(Long petId, Long userId) {
        Optional<Pet> pet = repository.findById(petId);
        Optional<User> user = usersRepository.findById(userId);
        if (pet.isEmpty() || user.isEmpty()) {
            return pet;
        }
        Pet foundPet = pet.get();
        User foundUser = user.get();
        foundPet.setOwner(foundUser);
        repository.save(foundPet);
        return pet;
    }

}
