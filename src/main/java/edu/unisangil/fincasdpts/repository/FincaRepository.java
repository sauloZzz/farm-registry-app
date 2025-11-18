package edu.unisangil.fincasdpts.repository;

import edu.unisangil.fincasdpts.entity.Finca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FincaRepository extends JpaRepository<Finca, Long> {
}
