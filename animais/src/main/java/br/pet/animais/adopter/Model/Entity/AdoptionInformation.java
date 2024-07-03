package br.pet.animais.adopter.Model.Entity;

import br.pet.animais.adopter.Model.Enums.PetLivingHouse;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "adoption_information")
public class AdoptionInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "cep")
    private String cep;

    @Column(name = "city")
    private String city;

    @Column(name = "house_type")
    private String houseType;

    @Enumerated(EnumType.STRING)
    @Column(name = "pet_living_house")
    private PetLivingHouse petLivingHouse;

    @Column(name = "number_pets")
    private int numberPets;

    @Column(name = "pet_ownership")
    private String petOwnership;

    @Column(name = "home_enviroment")
    private String homeEnvironment;

    @Column(name = "family_activity")
    private String familyActivity;

    @Column(name = "reasons_adoption")
    private String reasonsAdoption;

    @Column(name = "number_child")
    private int numberChild;

    @Column(name = "number_adults")
    private int numberAdults;

    @Column(name = "terms_and_condition")
    private String TermsAndConditions;

    @Column(name = "Pet")
    private String[] pet;
}
