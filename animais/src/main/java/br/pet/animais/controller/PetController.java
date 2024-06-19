package br.pet.animais.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import br.pet.animais.model.entity.*;

import br.pet.animais.Service.FileStorageService;
import br.pet.animais.repository.PetRepository;

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
    public List<Pet> getPet() {
        List<Pet> pets = repository.findAll();
        for (Pet pet : pets) {
            System.out.println("Pet ID: " + pet.getId() + pet.getPhotopetUrl());
        }
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> addPet(@RequestParam("name") String name,
                                    @RequestParam("age") String age,
                                    @RequestParam("size") String size,
                                    @RequestParam("gender") String gender,
                                    @RequestParam("situation") String situation,
                                    @RequestParam("veterinaryCare") String[] veterinaryCare,
                                    @RequestParam("photopet_url") MultipartFile photopetUrl) {

        try {
            Pet pet = new Pet();
            pet.setName(name);
            pet.setAge(age);
            pet.setSize(size);
            pet.setGender(gender);
            pet.setSituation(situation);
            pet.setVeterinaryCare(veterinaryCare);

            if (photopetUrl != null && !photopetUrl.isEmpty()) {
                String fileName = fileStorageService.storeFile(photopetUrl);
                String photoUrl = "/pet/photos/" + fileName;
                pet.setPhotopetUrl(photoUrl);
            }

            repository.save(pet);

            return ResponseEntity.ok().build();
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
