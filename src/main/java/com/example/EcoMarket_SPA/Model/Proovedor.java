package com.example.EcoMarket_SPA.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Proovedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String nombre;

    @Column(nullable = false)
    private String contacto;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany
    @JoinColumn(name = "proovedor_id")
    private List<Producto> productosSuministrados;
}
