package br.pet.animais.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import br.pet.animais.repository.PetRepository;
import br.pet.animais.model.entity.Pet;
import java.util.List;

@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetRepository repository;

    @GetMapping
    public List<Pet> GetPet(){
        return repository.findAll();
    }

    @PostMapping
    public Pet addPet(@RequestBody Pet pet){
         return repository.save(pet);
    }

    @PutMapping
    public Pet updatePet(@RequestBody Pet pet) {
        if (pet.getId() > 0)
            return repository.save(pet);
        return null;
    }

    @DeleteMapping
    public String delete(@RequestBody Pet pet){
        if (pet.getId() > 0){
            repository.delete(pet);
            return "Pet removido com sucesso";
        }
        return "Erro ao deletar pet";
    }

}
