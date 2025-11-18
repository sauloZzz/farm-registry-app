package edu.unisangil.fincasdpts.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "departamento")
@Data
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") // ← coincide con la tabla departamento(id)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
}
