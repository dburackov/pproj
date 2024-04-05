package com.dburackov.petfiesta.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;
import java.util.Set;


@Data
@Entity
@Table(name = "pet_profiles")
public class PetProfile {
    @Id
    @Column(name = "pet_profile_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "purpose")
    private String purpose;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="user_id")
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "petProfile", orphanRemoval = true)
    private Set<Photo> photos;

    @OneToOne(mappedBy = "petProfile", orphanRemoval = true)
    private Passport passport;

    @OneToMany(mappedBy = "petProfile", orphanRemoval = true)
    private Set<SocialMedia> socialMedias;

    @ManyToMany
    @JoinTable(
            name = "pet_xref_tag",
            joinColumns = @JoinColumn(name = "pet_profile_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags;

    @ElementCollection
    @CollectionTable (
            name = "likes",
            joinColumns = @JoinColumn(name = "target_pet_id")
    )
    @Column(name = "object_pet_id")
    private Set<Long> likedBy;

    @ElementCollection
    @CollectionTable(
            name = "likes",
            joinColumns = @JoinColumn(name = "object_pet_id")
    )
    @Column(name = "target_pet_id")
    private Set<Long> likedPetProfiles;

    @ElementCollection
    @CollectionTable(
            name="matches",
            joinColumns=@JoinColumn(name="second_pet_id")
    )
    @Column(name="first_pet_id")
    private Set<Long> matches;

    @ElementCollection
    @CollectionTable(
            name="viewed_profiles",
            joinColumns=@JoinColumn(name="object_pet_id")
    )
    @Column(name="target_pet_id")
    private Set<Long> viewedProfiles;
}
