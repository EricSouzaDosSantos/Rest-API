package br.pet.animais.pets.model.entity;

import br.pet.animais.pets.model.enums.*;
import br.pet.animais.pets.model.enums.*;
import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "petcontroller")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "lastTutorName")
    private String LastTutorName;

    @Column(name = "LastTutorEmail")
    private String LastTutorEmail;

    @Column(name = "lastTutorPhoneFix")
    private String lastTutorPhoneFix;

    @Column(name = "lastTutorPhoneCel")
    private String lastTutorPhoneCel;

    @Column(name = "lastTutorPreferences")
    private String lastTutorPreferencesContact;

    @Enumerated(EnumType.STRING)
    @Column(name = "species")
    private PetSpecies species;

    @Column(name = "petstory")
    private String petStory;

    @Enumerated(EnumType.STRING)
    @Column(name = "size")
    private Size size;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "age")
    private Age age;

    @Enumerated(EnumType.STRING)
    @Column(name = "situation")
    private Situation situation;

    @Column(name = "color")
    private String color;

    @Column(name = "temperament")
    private String[] temperament;

    @Column(name = "veterinarycare")
    private String[] veterinaryCare;

    @Column(name = "adaptability")
    private String[] adaptability;

    @Column(name = "socialization")
    private String[] socialization;

    @Column(name = "photopet_url")
    private String photopetUrl;
}
