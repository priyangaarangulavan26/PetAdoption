package com.petadopt.petadopt.repository;

import com.petadopt.petadopt.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {
}
