package dev.kaue.scheduling_system.dto;

import java.math.BigDecimal;

public record ServicoResponseDTO (
        Long id,
        String nomeServico,
        Integer duracaoServicoMinutos,
        BigDecimal preco,
        String nomeEmpresa
) {
}
