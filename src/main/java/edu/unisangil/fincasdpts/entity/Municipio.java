package edu.unisangil.fincasdpts.entity; // Asegúrate de que este es tu paquete real

import com.fasterxml.jackson.annotation.JsonBackReference; // <-- Importación CLAVE
import jakarta.persistence.*;

@Entity
@Table(name = "municipio")
public class Municipio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    // Relación Many-to-One
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departamento_id")
    // LADO CLAVE: @JsonBackReference indica a Jackson que IGNORE esta referencia
    // durante la serialización para evitar el bucle.
    @JsonBackReference
    private Departamento departamento;

    // Constructor vacío requerido por JPA
    public Municipio() {
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
}