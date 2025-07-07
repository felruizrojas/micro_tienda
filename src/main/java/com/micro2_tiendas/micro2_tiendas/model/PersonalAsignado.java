package com.micro2_tiendas.micro2_tiendas.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "personal_asignado")
public class PersonalAsignado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // ID interno propio del microservicio de tiendas

    @Column(length = 13, nullable = false)
    private String user; // Nombre de usuario (único), usado para comunicarte con microservicio de
                         // usuarios

    @Column(nullable = false)
    private boolean asignacionActiva = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tienda_id", nullable = false)
    private Tienda tienda;


}
