package edu.unisangil.fincasdpts.repository;

import edu.unisangil.fincasdpts.entity.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MunicipioRepository extends JpaRepository<Municipio, Long> {

    // Buscar municipios por id de departamento
    List<Municipio> findByDepartamento_Id(Long id);
}
