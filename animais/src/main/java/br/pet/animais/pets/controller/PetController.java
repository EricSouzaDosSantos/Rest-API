package br.pet.animais.pets.controller;

import br.pet.animais.pets.model.entity.Pet;
import br.pet.animais.pets.model.enums.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import br.pet.animais.pets.Service.FileStorageService;
import br.pet.animais.pets.repository.PetRepository;

import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetRepository repository;

    @Autowired
    private FileStorageService fileStorageService;

    @GetMapping
    public List<Pet> getAllPet() {
        List<Pet> pets = repository.findAll();
        /*
         * for (Pet pet : pets) {
         * System.out.println("Pet ID: " + pet.getId() +"\n" + pet.getPhotopetUrl());
         * }
         */
        return repository.findAll();
    }

    @GetMapping(path = { "/{id}" })
    public ResponseEntity getById(@PathVariable long id) {
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> addPet(@RequestParam("name") String name,
            @RequestParam("age") String ageStr,
            @RequestParam("species") String specieStr,
            @RequestParam("size") String sizeStr,
            @RequestParam("gender") String genderStr,
            @RequestParam("situation") String situationStr,
            @RequestParam("veterinarycare") String[] veterinaryCare,
            @RequestParam("petstory") String petStory,
            @RequestParam("color") String color,
            @RequestParam("temperament") String[] temperament,
            @RequestParam("adaptability") String[] adaptability,
            @RequestParam("socialization") String[] socialization,
            @RequestParam("lastTutorName") String lastTutorName,
            @RequestParam("lastTutorEmail") String lastTutorEmail,
            @RequestParam("lastTutorPhoneFix") String lastTutorPhoneFix,
            @RequestParam("lastTutorPhoneCel") String lastTutorPhoneCel,
            @RequestParam("lastTutorPreferences") String LastTutorPreferences,
            @RequestParam("photopet_url") MultipartFile photopetUrl) {

        try {
            Age age = Age.valueOf(ageStr.toUpperCase());
            PetSpecies specie = PetSpecies.valueOf(specieStr.toUpperCase());
            Size size = Size.valueOf(sizeStr.toUpperCase());
            Gender gender = Gender.valueOf(genderStr.toUpperCase());
            Situation situation = Situation.valueOf(situationStr.toUpperCase());

            Pet pet = new Pet();
            pet.setLastTutorName(lastTutorName);
            pet.setLastTutorEmail(lastTutorEmail);
            pet.setLastTutorPhoneFix(lastTutorPhoneFix);
            pet.setLastTutorPhoneCel(lastTutorPhoneCel);
            pet.setLastTutorPreferencesContact(LastTutorPreferences);
            pet.setName(name);
            pet.setSpecies(specie);
            pet.setAge(age);
            pet.setSize(size);
            pet.setGender(gender);
            pet.setSituation(situation);
            pet.setVeterinaryCare(veterinaryCare);
            pet.setPetStory(petStory);
            pet.setColor(color);
            pet.setSocialization(socialization);
            pet.setAdaptability(adaptability);
            pet.setTemperament(temperament);

            if (photopetUrl != null && !photopetUrl.isEmpty()) {
                String fileName = fileStorageService.storeFile(photopetUrl);
                String photoUrl = "/photos/" + fileName;
                pet.setPhotopetUrl(photoUrl);
            }

            repository.save(pet);

            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid enum value provided");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/photos/{fileName:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getPhoto(@PathVariable String fileName) {
        try {
            Path filePath = fileStorageService.loadFile(fileName);
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                return ResponseEntity.ok().body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
