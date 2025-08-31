// ✅ src/main/java/com/inclusive/authservice/model/Permission.java
package com.inclusive.authservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "permissions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // ✅ Constructor adicional para crear con solo el nombre
    public Permission(String name) {
        this.name = name;
    }
}







