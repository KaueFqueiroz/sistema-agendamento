package dev.kaue.scheduling_system.dto;

import java.math.BigDecimal;

public record ServicoRequestDTO (
    String nomeServico,
    Integer duracaoServicoMinutos,
    BigDecimal preco,
    Long empresaId
      )     {
}
