package dev.kaue.scheduling_system.controller;



import dev.kaue.scheduling_system.dto.AgendamentoResponseDTO;
import dev.kaue.scheduling_system.dto.AgendamentoRequestDTO;
import dev.kaue.scheduling_system.model.Agendamento;
import dev.kaue.scheduling_system.model.Cliente;
import dev.kaue.scheduling_system.model.Empresa;
import dev.kaue.scheduling_system.model.Servico;
import dev.kaue.scheduling_system.repository.AgendamentoRepository;
import dev.kaue.scheduling_system.repository.ClienteRepository;
import dev.kaue.scheduling_system.repository.EmpresaRepository;
import dev.kaue.scheduling_system.repository.ServicoRepository;
import dev.kaue.scheduling_system.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;
    private final ClienteRepository clienteRepository;
    private final ServicoRepository servicoRepository;
    private final EmpresaRepository empresaRepository;
    private final AgendamentoRepository agendamentoRepository;

    @Autowired
    public AgendamentoController(AgendamentoService agendamentoService,
                                 ClienteRepository clienteRepository,
                                 ServicoRepository servicoRepository,
                                 EmpresaRepository empresaRepository,
                                 AgendamentoRepository agendamentoRepository)
    {
        this.agendamentoService = agendamentoService;
        this.clienteRepository = clienteRepository;
        this.servicoRepository = servicoRepository;
        this.empresaRepository = empresaRepository;
        this.agendamentoRepository = agendamentoRepository;
    }

    @PostMapping
    public AgendamentoResponseDTO criarAgendamento(@RequestBody AgendamentoRequestDTO requestDTO){
        Cliente cliente = clienteRepository.findById(requestDTO.clienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Servico servico = servicoRepository.findById(requestDTO.servicoId())
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado"));

        Agendamento agendamento = new Agendamento();
        agendamento.setCliente(cliente);
        agendamento.setServico(servico);
        agendamento.setDataHoraAgendada(requestDTO.dataHoraAgendada());

        Agendamento agendamentoSalvo = agendamentoService.cadastrarAgendamento(agendamento);

        return new AgendamentoResponseDTO(
                agendamentoSalvo.getId(),
                agendamentoSalvo.getCliente().getNomeCliente(),
                agendamentoSalvo.getServico().getNomeServico(),
                agendamentoSalvo.getServico().getPreco(),
                agendamentoSalvo.getDataHoraAgendada(),
                agendamentoSalvo.getStatus()
        );
    }

    @GetMapping("/{empresaId}")
    public List<AgendamentoResponseDTO> listarAgendamentoPorEmpresa(@PathVariable Long empresaId){
        Empresa empresa = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));

        List<Agendamento> agendamentos = agendamentoRepository.findByServico_Empresa(empresa);

        List<AgendamentoResponseDTO> listaAgendamentos = new ArrayList<>();

        for (Agendamento agendamentoAtual : agendamentos) {
            AgendamentoResponseDTO dto = new AgendamentoResponseDTO(
                    agendamentoAtual.getId(),
                    agendamentoAtual.getCliente().getNomeCliente(),
                    agendamentoAtual.getServico().getNomeServico(),
                    agendamentoAtual.getServico().getPreco(),
                    agendamentoAtual.getDataHoraAgendada(),
                    agendamentoAtual.getStatus()

            );
            listaAgendamentos.add(dto);
        }
        return listaAgendamentos;
    }



}
