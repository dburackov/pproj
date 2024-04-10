package com.dburackov.petfiesta.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.LinkedHashSet;
import java.util.List;
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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "pet_xref_tag",
            joinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "tag_id"),
            inverseJoinColumns = @JoinColumn(name = "pet_profile_id", referencedColumnName = "pet_profile_id")
    )
    private Set<PetProfile> petProfiles = new LinkedHashSet<>();
}
