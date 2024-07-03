package br.pet.animais.adopter.Controller;

import br.pet.animais.adopter.Model.Enums.PetLivingHouse;
import br.pet.animais.adopter.Repository.AdoptionRepository;
import br.pet.animais.adopter.Model.Entity.AdoptionInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/adopter")
public class AdoptionController {

    @Autowired
    private AdoptionRepository adoptionRepository;

    @GetMapping
    public List<AdoptionInformation> getAll(){
        List<AdoptionInformation> adoption = adoptionRepository.findAll();
        return adoption;
    }

    @GetMapping(path = { "/{id}" })
    public ResponseEntity getById(@PathVariable long id) {
        return adoptionRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> addAdopter(@RequestParam("name") String name,
                                        @RequestParam("email") String email,
                                        @RequestParam("address") String address,
                                        @RequestParam("cep") String cep,
                                        @RequestParam("city") String city,
                                        @RequestParam("house_type") String houseType,
                                        @RequestParam("pet_living_house") String petLivingHouse,
                                        @RequestParam("number_pets") int numberPets,
                                        @RequestParam("pet_ownership") String petOwnership,
                                        @RequestParam("home_environment") String homeEnvironment,
                                        @RequestParam("family_activity") String familyActivity,
                                        @RequestParam("reasons_adoption") String reasonsAdoption,
                                        @RequestParam("number_child") int numberChild,
                                        @RequestParam("number_adults") int numberAdults,
                                        @RequestParam("terms_and_conditions") String termsAndConditions,
                                        @RequestParam("Pet")String[] pet){

        try {
        PetLivingHouse petLiving = PetLivingHouse.valueOf(petLivingHouse.toUpperCase());

        AdoptionInformation adopter = new AdoptionInformation();
        adopter.setName(name);
        adopter.setEmail(email);
        adopter.setAddress(address);
        adopter.setCep(cep);
        adopter.setCity(city);
        adopter.setHouseType(houseType);
        adopter.setPetLivingHouse(petLiving);
        adopter.setNumberPets(numberPets);
        adopter.setPetOwnership(petOwnership);
        adopter.setHomeEnvironment(homeEnvironment);
        adopter.setFamilyActivity(familyActivity);
        adopter.setReasonsAdoption(reasonsAdoption);
        adopter.setNumberChild(numberChild);
        adopter.setNumberAdults(numberAdults);
        adopter.setTermsAndConditions(termsAndConditions);
        adopter.setPet(pet);
        adoptionRepository.save(adopter);

            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid enum value provided");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}


