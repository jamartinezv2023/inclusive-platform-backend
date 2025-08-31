// src/main/java/com/inclusive/authservice/model/Profile.java
package com.inclusive.authservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String descripcion;

    public Profile(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
}




