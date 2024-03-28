package com.dburackov.petfiesta.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "pet_xref_tag")
public class PetXrefTag {
    @Column(name = "pet_profile_id")
    private Long petProfileId;

    @Column(name = "tag_id")
    private Long tagId;
}
