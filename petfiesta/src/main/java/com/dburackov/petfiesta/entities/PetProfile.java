package com.dburackov.petfiesta.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;



@Data
@Entity
@Table(name = "pet_profiles")
public class PetProfile {
    @Id
    @Column(name = "pet_profile_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

//    @Column(name = "user_id")
//    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @Column(name = "purpose")
    private String purpose;
}
