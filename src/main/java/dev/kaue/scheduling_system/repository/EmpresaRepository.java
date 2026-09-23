package dev.kaue.scheduling_system.repository;

import dev.kaue.scheduling_system.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
}
