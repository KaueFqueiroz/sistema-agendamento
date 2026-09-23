package dev.kaue.scheduling_system.repository;

import dev.kaue.scheduling_system.model.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<Servico, Long> {
}
