package com.dburackov.petfiesta.entities;


import jakarta.persistence.*;
import lombok.Data;

import java.util.LinkedHashSet;
import java.util.Set;


@Data
@Entity
@Table(name = "tags")
public class Tag {
    @Id
    @Column(name = "tag_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "pet_xref_tag",
            joinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "tag_id"),
            inverseJoinColumns = @JoinColumn(name = "pet_profile_id", referencedColumnName = "pet_profile_id")
    )
    private Set<PetProfile> petProfiles = new LinkedHashSet<>();
}
