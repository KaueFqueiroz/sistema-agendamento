package dev.kaue.scheduling_system.dto;


public record EmpresaRequestDTO (
        String nomeEmpresa,
        String horarioAbertura,
        String horarioFechamento,
        String chavePix
) {

}
