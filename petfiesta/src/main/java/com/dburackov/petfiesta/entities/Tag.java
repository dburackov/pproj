package com.dburackov.petfiesta.entities;


import jakarta.persistence.*;
import lombok.Data;


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

}
