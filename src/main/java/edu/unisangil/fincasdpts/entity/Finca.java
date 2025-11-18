package edu.unisangil.fincasdpts.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "finca")
@Data
public class Finca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") // ← coincide con la tabla finca(id)
    private Long id;

    @Column(name = "nombre_propietario", nullable = false, length = 150)
    private String nombrePropietario;

    @ManyToOne
    @JoinColumn(name = "departamento_id", nullable = false) // FK finca.departamento_id → departamento.id
    private Departamento departamento;

    @ManyToOne
    @JoinColumn(name = "municipio_id", nullable = false) // FK finca.municipio_id → municipio.id
    private Municipio municipio;

    @Column(name = "direccion", nullable = false, length = 255)
    private String direccion;

    @Column(name = "telefono", nullable = false, length = 30)
    private String telefono;
}
