package dev.kaue.scheduling_system.repository;

import dev.kaue.scheduling_system.model.Agendamento;
import dev.kaue.scheduling_system.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {


    List<Agendamento> findByServico_Empresa(Empresa empresa);
}

