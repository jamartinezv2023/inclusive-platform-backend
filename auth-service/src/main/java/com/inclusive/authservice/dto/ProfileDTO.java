// src/main/java/com/inclusive/authservice/dto/ProfileDTO.java
package com.inclusive.authservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDTO {
    private Long id;
    private String nombre;
    private String descripcion;
}
