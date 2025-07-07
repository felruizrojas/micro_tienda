package com.micro2_tiendas.micro2_tiendas.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tiendas")
public class Tienda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTienda;

    @Column(length = 250, nullable = false)
    private String direccion;

    @Column(length = 100, nullable = false)
    private String ciudad;

    @Column(length = 100, nullable = false)
    private String region;

    @Column(length = 500)
    private String politicasLocales;

    @Column(nullable = false)
    private LocalTime horaApertura;

    @Column(nullable = false)
    private boolean activa = true;

    @OneToMany(mappedBy = "tienda", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PersonalAsignado> personalAsignado;
}
