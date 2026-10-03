package com.petadopt.petadopt.controller;

import com.petadopt.petadopt.model.Adoption;
import com.petadopt.petadopt.repository.AdoptionRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/adoptions")
@CrossOrigin
public class AdoptionController {

    private final AdoptionRepository adoptionRepository;

    public AdoptionController(AdoptionRepository adoptionRepository) {
        this.adoptionRepository = adoptionRepository;
    }

    // Get all adoption applications
    @GetMapping
    public List<Adoption> getAllAdoptions() {
        return adoptionRepository.findAll();
    }

    // Submit a new adoption application
    @PostMapping
    public Adoption addAdoption(@RequestBody Adoption adoption) {
        return adoptionRepository.save(adoption);
    }

    // Get adoption application by ID
    @GetMapping("/{id}")
    public Adoption getAdoptionById(@PathVariable Long id) {
        return adoptionRepository.findById(id).orElse(null);
    }

    // Delete adoption application
    @DeleteMapping("/{id}")
    public String deleteAdoption(@PathVariable Long id) {

        adoptionRepository.deleteById(id);

        return "Adoption application deleted successfully";
    }
}
