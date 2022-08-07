package com.chiche.spring.jpa.postgresql.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "SALES_DATA")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SalesData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "commune", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Commune commune;

    @Column(name = "nombre_vente")
    private int nombreVente;
    @Column(name = "prix_m2")
    private int prixM2;
    @Column(name = "nombre_vefa")
    private int nombreVefa;
    @Column(name = "prix_m2_vefa")
    private int prixM2Vefa;


}
