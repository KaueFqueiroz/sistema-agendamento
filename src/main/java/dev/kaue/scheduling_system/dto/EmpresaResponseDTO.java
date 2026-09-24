package dev.kaue.scheduling_system.dto;

public record EmpresaResponseDTO(
        Long id,
        String nomeEmpresa,
        String horarioAbertura,
        String horarioFechamento,
        String chavePix
){
}
