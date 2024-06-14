package br.pet.animais.model.entity;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Arrays;

@Data
@Entity
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "petstory")
    private String petStory;

    @Column(name = "size")
    private String size;

    @Column(name = "gender")
    private String gender;

    @Column(name = "age")
    private String age;

    @Column(name = "situation")
    private String situation;

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


    @Column(name = "photopet")
    @Lob
    private Byte[] photopet;

}
