package com.myevent.domain.entity;

import com.myevent.domain.enums.StatusEventoEnum;
import com.myevent.domain.enums.converter.StatusEventoConverter;
import com.myevent.domain.enums.converter.StatusIngressoConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import java.time.LocalDateTime;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@Entity
@Builder
@Audited
@NoArgsConstructor
@AllArgsConstructor
@Table(name = Evento.TABLE_NAME)
public class Evento {

    public static final String TABLE_NAME = "tb_evento";
    public static final String SEQ_NAME = "tb_evento_seq";

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    @Column(name = "id_evento", nullable = false)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "id_organizador")
    private Usuario organizador;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "data_evento")
    private LocalDateTime data;

    @Column(name = "capacidade")
    private Integer capacidade;

    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    @OneToOne
    @JoinColumn (name = "id_endereco")
    private Endereco endereco;

//    @OneToMany(mappedBy = "evento", fetch = LAZY)
//    private List<Ingresso> ingressos;

    @Column (name = "status")
    @Convert(converter = StatusEventoConverter.class)
    private StatusEventoEnum status;

    @Column(name = "possui_espera")
    private Boolean possuiEspera = false;
/*
Além de "Ativo" e "Inativo", Status tbm deveria ter "Espera" talvez, para englobar o Status do evento

    @Column(name = "status")
    @Convert(converter = StatusConverter.class)
    private StatusEnum status;
 */
}
