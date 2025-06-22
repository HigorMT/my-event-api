package com.myevent.domain.entity;

import com.myevent.domain.enums.StatusIngressoEnum;
import com.myevent.domain.enums.converter.StatusIngressoConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@Entity
@Builder
@Audited
@NoArgsConstructor
@AllArgsConstructor
@Table(name = Ingresso.TABLE_NAME)
public class Ingresso {

    public static final String TABLE_NAME = "tb_ingresso";
    public static final String SEQ_NAME = "tb_ingresso_seq";

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    @Column(name = "id_ingresso", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuarioCadastro;

    @OneToOne
    @JoinColumn(name = "id_titular")
    private Pessoa titular;

    @Column (name = "data_pagamento")
    private LocalDate dataPagamento;

    @Column (name = "valor_ingresso")
    private BigDecimal valorIngresso;

    @Column (name = "status")
    @Convert(converter = StatusIngressoConverter.class)
    private StatusIngressoEnum status;

    @Column(name = "id_evento")
    private Long eventoId;

    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    @ManyToOne
    @JoinColumn(name = "id_evento", insertable = false, updatable = false)
    private Evento evento;

}
