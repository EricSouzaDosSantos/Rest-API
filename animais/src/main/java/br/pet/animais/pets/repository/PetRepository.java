package br.pet.animais.pets.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.pet.animais.pets.model.entity.Pet;


public interface PetRepository extends JpaRepository<Pet, Long>{

}
