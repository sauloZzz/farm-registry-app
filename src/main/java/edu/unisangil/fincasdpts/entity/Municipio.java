package edu.unisangil.fincasdpts.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "municipio")
@Data
public class Municipio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") // ← coincide con la tabla municipio(id)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "departamento_id", nullable = false) // FK municipio.departamento_id → departamento.id
    private Departamento departamento;
}
