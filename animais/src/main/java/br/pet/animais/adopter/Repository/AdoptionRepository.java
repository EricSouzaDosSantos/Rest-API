package br.pet.animais.adopter.Repository;

import br.pet.animais.adopter.Model.Entity.AdoptionInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdoptionRepository extends JpaRepository<AdoptionInformation, Long> {
}
