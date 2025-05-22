package com.example.EcoMarket_SPA.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Proovedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private String contacto;
    private String email;

    @OneToMany(mappedBy = "proovedor", cascade = CascadeType.ALL)
    private List<Producto> productosSuministrados;
}