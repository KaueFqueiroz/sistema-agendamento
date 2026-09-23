package dev.kaue.scheduling_system.controller;



import dev.kaue.scheduling_system.dto.AgendamentoResponseDTO;
import dev.kaue.scheduling_system.dto.AgendamentoRequestDTO;
import dev.kaue.scheduling_system.model.Agendamento;
import dev.kaue.scheduling_system.model.Cliente;
import dev.kaue.scheduling_system.model.Servico;
import dev.kaue.scheduling_system.repository.ClienteRepository;
import dev.kaue.scheduling_system.repository.ServicoRepository;
import dev.kaue.scheduling_system.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;
    private final ClienteRepository clienteRepository;
    private final ServicoRepository servicoRepository;

    @Autowired
    public AgendamentoController(AgendamentoService agendamentoService,
                                 ClienteRepository clienteRepository,
                                 ServicoRepository servicoRepository)
    {
        this.agendamentoService = agendamentoService;
        this.clienteRepository = clienteRepository;
        this.servicoRepository = servicoRepository;
    }

    @PostMapping
    public AgendamentoResponseDTO criarAgendamento(@RequestBody AgendamentoRequestDTO requestDTO){
        Cliente cliente = clienteRepository.findById(requestDTO.clienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Servico servico = servicoRepository.findById(requestDTO.servicoId())
                .orElseThrow(() -> new RuntimeException("Serviõ não encontrado"));

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
                agendamentoSalvo.getDataHoraAgendada()
        );
    }



}
