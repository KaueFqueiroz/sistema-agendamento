package dev.kaue.scheduling_system.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "servico")
@Getter
@Setter
@NoArgsConstructor
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeServico;

    private Integer duracaoServicoMinutos;

    private BigDecimal preco;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;
}
