package com.myevent.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@Entity
@Builder
@Audited
@NoArgsConstructor
@AllArgsConstructor
@Table(name = Endereco.TABLE_NAME)
public class Endereco {

    public static final String TABLE_NAME = "tb_endereco";
    public static final String SEQ_NAME = "tb_endereco_seq";

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    @Column(name = "id_endereco", nullable = false)
    private Long id;

    @Column (name = "cep", nullable = false)
    private String cep;

    @OneToOne
    @JoinColumn(name = "id_cidade", nullable = false)
    private Cidade cidade;

    @Column (name = "logradouro", nullable = false)
    private String logradouro;

    @Column (name = "bairro", nullable = false)
    private String bairro;

    @Column (name = "numero", nullable = false)
    private Integer numero;
}
