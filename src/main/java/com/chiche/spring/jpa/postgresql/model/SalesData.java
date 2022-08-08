package com.chiche.spring.jpa.postgresql.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Sales_Data")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SalesData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "commune_id", nullable = false)
    private Commune commune ;

    @Column(name = "nombre_vente")
    private int nombreVente;
    @Column(name = "prix_m2")
    private int prixM2;
    @Column(name = "nombre_vefa")
    private int nombreVefa;
    @Column(name = "prix_m2_vefa")
    private int prixM2Vefa;




}
