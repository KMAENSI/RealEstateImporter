package com.chiche.spring.jpa.postgresql.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "COMMUNE")
@Getter
@Setter
public class Commune {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "description")
    private String description;

    @OneToMany(targetEntity=SalesData.class, mappedBy="commune",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SalesData> salesDatas = new ArrayList<>();

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
