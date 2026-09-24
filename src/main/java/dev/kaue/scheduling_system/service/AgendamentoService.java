package dev.kaue.scheduling_system.service;

import dev.kaue.scheduling_system.exception.HorarioIndisponivelException;
import dev.kaue.scheduling_system.model.Agendamento;
import dev.kaue.scheduling_system.model.StatusAgendamento;
import dev.kaue.scheduling_system.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;

    @Autowired
    public AgendamentoService(AgendamentoRepository agendamentoRepository){
        this.agendamentoRepository = agendamentoRepository;
    }

    public void verificarDisponibilidadeAgendamento(Agendamento novoAgendamento){
       List<Agendamento> agendamentoDaEmpresa =  agendamentoRepository.findByServico_Empresa(novoAgendamento.getServico().getEmpresa());
        for (Agendamento agendamentoExistente : agendamentoDaEmpresa){
            LocalDateTime inicioExistente = agendamentoExistente.getDataHoraAgendada();
            LocalDateTime fimExistente = inicioExistente.plusMinutes(agendamentoExistente.getServico().getDuracaoServicoMinutos());
            LocalDateTime inicioNovo = novoAgendamento.getDataHoraAgendada();
            LocalDateTime fimNovo = inicioNovo.plusMinutes(novoAgendamento.getServico().getDuracaoServicoMinutos());

            boolean semConflito = fimExistente.isBefore(inicioNovo) || fimNovo.isBefore(inicioExistente);  // inicioNovo comçea depois do fimExistente


            if (!semConflito){
                throw new HorarioIndisponivelException("Horário indisponível para este agendamento");
            }
        }
    }

    public Agendamento cadastrarAgendamento(Agendamento novoAgendamento){
       verificarDisponibilidadeAgendamento(novoAgendamento);

       novoAgendamento.setStatus(StatusAgendamento.PENDENTE_PAGAMENTO);

       Agendamento agendamento = agendamentoRepository.save(novoAgendamento);

        return agendamento;

    }
}
