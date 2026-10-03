package com.petadopt.petadopt.repository;

import com.petadopt.petadopt.model.Adoption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdoptionRepository extends JpaRepository<Adoption, Long> {
}