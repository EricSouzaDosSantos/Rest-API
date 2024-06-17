package br.pet.animais.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.pet.animais.repository.PetRepository;
import br.pet.animais.model.entity.Pet;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetRepository repository;

    /*
    @GetMapping
     public List<Pet> GetPet(){
         return repository.findAll();
     }
     /


     */

     /*
    @GetMapping("")
    public Optional<Pet> GetPetForId(@RequestBody Pet pet) {
        if(pet.getId() != 0)

            return repository.findById(pet.getId());

         return null;
    }

      */


    @GetMapping
    public List<Pet> GetPet() {
        List<Pet> pets = repository.findAll();
        for (Pet pet : pets) {
            System.out.println("Pet ID: " + pet.getId());
            System.out.println("Photopet Length: " + (pet.getPhotopet() != null ? pet.getPhotopet().length : "null"));
        }
        return pets;
    }

    @PostMapping
    public ResponseEntity<?> addPet(@RequestParam("name") String name,
                                    @RequestParam("age") String age,
                                    @RequestParam("size") String size,
                                    @RequestParam("gender") String gender,
                                    @RequestParam("situation") String situation,
                                    @RequestParam("photopet") MultipartFile photopet) {

        try {
            Pet pet = new Pet();
            pet.setName(name);
            System.out.println(photopet.getBytes() +"\n \n"+ name);
            pet.setAge(age);
            pet.setSize(size);
            pet.setGender(gender);
            pet.setSituation(situation);

            if (photopet != null && !photopet.isEmpty()) {

               // byte[] imagem = photopet.getInputStream().readAllBytes();
                pet.setPhotopet(photopet.getBytes());

            }

            repository.save(pet);

            return ResponseEntity.ok().build();

        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("There was an error with the uploaded image.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // ... (your existing updatePet and delete methods)


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
