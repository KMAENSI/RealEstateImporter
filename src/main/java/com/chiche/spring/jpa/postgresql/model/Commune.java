package com.chiche.spring.jpa.postgresql.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
@Entity
@Table(name = "COMMUNE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Commune {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "commune_id")
    private Integer communeId;

    @Column(name = "description")
    private String description;

    public Commune(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Commune  [id=" + communeId + ", description=" + description + "]";
    }
}
