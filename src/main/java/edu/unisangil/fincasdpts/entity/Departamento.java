package edu.unisangil.fincasdpts.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference; // <-- Importación CLAVE
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "departamento")
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    // LADO CLAVE: @JsonManagedReference indica a Jackson que serialice esta colección.
    @OneToMany(mappedBy = "departamento", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Municipio> municipios;

    // Constructor vacío requerido por JPA
    public Departamento() {
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

    public List<Municipio> getMunicipios() {
        return municipios;
    }

    public void setMunicipios(List<Municipio> municipios) {
        this.municipios = municipios;
    }
}