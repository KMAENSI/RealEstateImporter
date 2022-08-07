package com.chiche.spring.jpa.postgresql.model;

import javax.persistence.*;

@Entity
@Table(name = "COMMUNE")
public class Commune {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "description")
    private String description;

    public Commune() {

    }
    public Commune(String description) {
        this.description = description;
    }
    public long getId() {
        return id;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public String toString() {
        return "Commune  [id=" + id + ", description=" + description + "]";
    }
}
