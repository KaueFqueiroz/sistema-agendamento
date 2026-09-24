package dev.kaue.scheduling_system.dto;

import dev.kaue.scheduling_system.model.StatusAgendamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record AgendamentoResponseDTO(
    Long id,
    String nomeCliente,
    String nomeServico,
    BigDecimal precoServico,
    LocalDateTime dataHoraAgendada,
    StatusAgendamento status
    ) {
}
