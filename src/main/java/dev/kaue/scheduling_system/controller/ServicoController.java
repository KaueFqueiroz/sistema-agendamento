package dev.kaue.scheduling_system.controller;


import dev.kaue.scheduling_system.dto.ServicoRequestDTO;
import dev.kaue.scheduling_system.dto.ServicoResponseDTO;
import dev.kaue.scheduling_system.model.Empresa;
import dev.kaue.scheduling_system.model.Servico;
import dev.kaue.scheduling_system.repository.EmpresaRepository;
import dev.kaue.scheduling_system.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/servicos")
public class ServicoController {


    private final ServicoRepository servicoRepository;
    private final EmpresaRepository empresaRepository;

    @Autowired
    public ServicoController(ServicoRepository servicoRepository,
                             EmpresaRepository empresaRepository)
    {
        this.servicoRepository = servicoRepository;
        this.empresaRepository = empresaRepository;
    }

    @PostMapping
    public ServicoResponseDTO criarServico(@RequestBody ServicoRequestDTO requestDTO){

        Empresa empresa = empresaRepository.findById(requestDTO.empresaId())
                .orElseThrow(() -> new RuntimeException("Empresa não encontrado"));

        Servico servico = new Servico();
        servico.setNomeServico(requestDTO.nomeServico());
        servico.setDuracaoServicoMinutos(requestDTO.duracaoServicoMinutos());
        servico.setPreco(requestDTO.preco());
        servico.setEmpresa(empresa);

        Servico servicoSalvo = servicoRepository.save(servico);

        return new ServicoResponseDTO(
                servicoSalvo.getId(),
                servicoSalvo.getNomeServico(),
                servicoSalvo.getDuracaoServicoMinutos(),
                servicoSalvo.getPreco(),
                servicoSalvo.getEmpresa().getNomeEmpresa()
        );

    }


    @GetMapping
    public List<ServicoResponseDTO> listarServicos(){
        List<Servico> servicos = servicoRepository.findAll();

        List<ServicoResponseDTO> listaServicos = new ArrayList<>();

        for (Servico servicoAtual : servicos){
            ServicoResponseDTO dto = new ServicoResponseDTO(
                    servicoAtual.getId(),
                    servicoAtual.getNomeServico(),
                    servicoAtual.getDuracaoServicoMinutos(),
                    servicoAtual.getPreco(),
                    servicoAtual.getEmpresa().getNomeEmpresa()
            );

            listaServicos.add(dto);
        }

        return listaServicos;
    }



}
