package dev.kaue.scheduling_system.controller;

import dev.kaue.scheduling_system.dto.EmpresaRequestDTO;
import dev.kaue.scheduling_system.dto.EmpresaResponseDTO;
import dev.kaue.scheduling_system.model.Empresa;
import dev.kaue.scheduling_system.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {


    private final EmpresaRepository empresaRepository;
    @Autowired
    public EmpresaController (EmpresaRepository empresaRepository){
       this.empresaRepository = empresaRepository;
    }

    @PostMapping
    public EmpresaResponseDTO criarEmpresa(@RequestBody EmpresaRequestDTO requestDTO){

        Empresa empresa = new Empresa();
        empresa.setNomeEmpresa(requestDTO.nomeEmpresa());
        empresa.setHorarioAbertura(requestDTO.horarioAbertura());
        empresa.setHorarioFechamento(requestDTO.horarioFechamento());
        empresa.setChavePix(requestDTO.chavePix());

        Empresa empresaSalva = empresaRepository.save(empresa);

        return new EmpresaResponseDTO(
                empresaSalva.getId(),
                empresaSalva.getNomeEmpresa(),
                empresaSalva.getHorarioAbertura(),
                empresaSalva.getHorarioFechamento(),
                empresaSalva.getChavePix()
        );
    }

    @GetMapping
    public List<EmpresaResponseDTO> listarEmpresas(){
        List<Empresa> empresas = empresaRepository.findAll();

        List<EmpresaResponseDTO> listaEmpresas = new ArrayList<>();

        for (Empresa empresaAtual : empresas){
            EmpresaResponseDTO dto = new EmpresaResponseDTO(
                    empresaAtual.getId(),
                    empresaAtual.getNomeEmpresa(),
                    empresaAtual.getHorarioAbertura(),
                    empresaAtual.getHorarioFechamento(),
                    empresaAtual.getChavePix()
            );
            listaEmpresas.add(dto);
        }
        return listaEmpresas;
    }


}
