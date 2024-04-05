package com.dburackov.petfiesta.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;


@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "user")
    private Set<PetProfile> petProfiles;

}
