package com.dburackov.petfiesta.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tags")
public class Tags {
    @Id
    @Column(name = "tag_id")
    private Long tagId;

    @Column(name = "name")
    private String name;

}
