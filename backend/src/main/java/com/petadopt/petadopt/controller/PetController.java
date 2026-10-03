package com.petadopt.petadopt.controller;

import com.petadopt.petadopt.model.Pet;
import com.petadopt.petadopt.repository.PetRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pets")
@CrossOrigin
public class PetController {

    private final PetRepository petRepository;

    public PetController(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    // Get all pets
    @GetMapping
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    // Get pet by ID
    @GetMapping("/{id}")
    public Pet getPetById(@PathVariable Long id) {
        return petRepository.findById(id).orElse(null);
    }

    // Add a new pet
    @PostMapping
    public Pet addPet(@RequestBody Pet pet) {
        return petRepository.save(pet);
    }

    // Update a pet
    @PutMapping("/{id}")
    public Pet updatePet(@PathVariable Long id, @RequestBody Pet pet) {

        Pet existingPet = petRepository.findById(id).orElse(null);

        if (existingPet == null) {
            return null;
        }

        existingPet.setName(pet.getName());
        existingPet.setBreed(pet.getBreed());
        existingPet.setAge(pet.getAge());
        existingPet.setGender(pet.getGender());
        existingPet.setType(pet.getType());
        existingPet.setDescription(pet.getDescription());
        existingPet.setLocation(pet.getLocation());
        existingPet.setAdoptionStatus(pet.getAdoptionStatus());
        existingPet.setImageUrl(pet.getImageUrl());

        return petRepository.save(existingPet);
    }

    // Delete a pet
    @DeleteMapping("/{id}")
    public String deletePet(@PathVariable Long id) {

        petRepository.deleteById(id);

        return "Pet deleted successfully";
    }
}