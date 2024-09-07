package com.acme.web.services.management.domain.model.entities;

import com.acme.web.services.management.domain.model.aggregates.Cage;
import com.acme.web.services.management.domain.model.commands.CreateAnimalCommand;
import com.acme.web.services.management.domain.model.valueobjects.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Animal entity
 * It contains the attributes of the Animal, the constructor and the getters.
 * @author Giacomo Zoppi Rodriguez - u2022210029
 * @version 1.0
 */
@Getter
@Entity
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @Embedded
    private Name name;
    @Setter
    @Enumerated(EnumType.STRING)
    private Breed breed;
    @Setter
    @Embedded
    private Gender gender;
    @Setter
    @Embedded
    private Birthdate birthdate;
    @Setter
    @Embedded
    private Weight weight;
    @Setter
    @Embedded
    private IsSick isSick;
    @Setter
    @Embedded
    private Observations observations;
    @ManyToOne
    @JoinColumn(name = "cage_id")
    private Cage cage;

    public Animal() {}

    /**
     * Constructor
     * @param name
     * @param breed
     * @param gender
     * @param birthdate
     * @param weight
     * @param isSick
     * @param observations
     * @param cage
     */
    public Animal(String name, String breed, Boolean gender, LocalDate birthdate, Float weight, Boolean isSick, String observations, Cage cage) {
        this.name = new Name(name);
        this.breed = Breed.valueOf(breed.toUpperCase());
        this.gender = new Gender(gender);
        this.birthdate = new Birthdate(birthdate);
        this.weight = new Weight(weight);
        this.isSick = new IsSick(isSick);
        this.observations = new Observations(observations);
        this.cage = cage;
    }

    /**
     * Constructor
     * @param name
     * @param breed
     * @param gender
     * @param birthdate
     * @param weight
     * @param isSick
     * @param observations
     * @param cage
     */
    public Animal(Name name, Breed breed, Gender gender, Birthdate birthdate, Weight weight, IsSick isSick, Observations observations, Cage cage) {
        this.name = name;
        this.breed = breed;
        this.gender = gender;
        this.birthdate = birthdate;
        this.weight = weight;
        this.isSick = isSick;
        this.observations = observations;
        this.cage = cage;
    }

    /**
     * Constructor
     * @param command
     * @param cage
     */
    public Animal(CreateAnimalCommand command, Cage cage) {
        this.name = new Name(command.name());
        this.breed = Breed.valueOf(command.breed().toUpperCase());
        this.gender = new Gender(command.gender());
        this.birthdate = new Birthdate(command.birthdate());
        this.weight = new Weight(command.weight());
        this.isSick = new IsSick(command.isSick());
        this.observations = new Observations(command.observations());
        this.cage = cage;
    }

    // Getters
    public String name() {
        return this.name.name();
    }
    public String breed() {
        return this.breed != null ? this.breed.toString() : "OTROS";
    }
    public boolean gender() {
        return this.gender.gender();
    }
    public LocalDate birthdate() {
        return this.birthdate.birthdate();
    }
    public Float weight() {
        return this.weight.weight();
    }
    public boolean isSick() {
        return this.isSick.isSick();
    }
    public String observations() {
        return this.observations.observations();
    }
}
