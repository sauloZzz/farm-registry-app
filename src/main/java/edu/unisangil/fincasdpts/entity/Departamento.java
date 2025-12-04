package edu.unisangil.fincasdpts.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "departamento")
@Data
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @OneToMany(mappedBy = "departamento", cascade = CascadeType.ALL)
    private List<Municipio> municipios;
}
