package dev.kaue.scheduling_system.repository;

import dev.kaue.scheduling_system.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
}
