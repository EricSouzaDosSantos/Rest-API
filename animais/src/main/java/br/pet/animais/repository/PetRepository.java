package br.pet.animais.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.pet.animais.model.entity.Pet;


public interface PetRepository extends JpaRepository<Pet, Long>{

}
