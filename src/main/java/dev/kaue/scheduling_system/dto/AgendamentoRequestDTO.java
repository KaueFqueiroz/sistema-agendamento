package dev.kaue.scheduling_system.dto; // mesmo pacote do outro DTO

import java.time.LocalDateTime;

public record AgendamentoRequestDTO( // representa o que chega na API pra criar um agendamento
                                     Long clienteId,         // id do cliente que está agendando
                                     Long servicoId,         // id do serviço escolhido
                                     LocalDateTime dataHoraAgendada // quando o cliente quer marcar
) {
}