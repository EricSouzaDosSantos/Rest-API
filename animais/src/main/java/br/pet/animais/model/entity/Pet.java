package br.pet.animais.model.entity;

import br.pet.animais.model.enums.*;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "petcontroller")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

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

    @ElementCollection
    @Column(name = "temperament")
    private String[] temperament;

    @ElementCollection
    @Column(name = "veterinarycare")
    private String[] veterinaryCare;

    @ElementCollection
    @Column(name = "adaptability")
    private String[] adaptability;

    @ElementCollection
    @Column(name = "socialization")
    private String[] socialization;

    @Column(name = "photopet_url")
    private String photopetUrl;
}
