package com.myevent.domain.entity;

import com.myevent.domain.enums.EstadoEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@Entity
@Builder
@Audited
@NoArgsConstructor
@AllArgsConstructor
@Table(name = Ingresso.TABLE_NAME)
public class Cidade {

    public static final String TABLE_NAME = "tb_cidade";
    public static final String SEQ_NAME = "tb_cidade_seq";

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    @Column(name = "id_cidade", nullable = false)
    private Long id;

    @Column(name = "nome", length = 255)
    private String nome;

    @Enumerated(STRING)
    @Column(name = "estado")
    private EstadoEnum estado;

}
