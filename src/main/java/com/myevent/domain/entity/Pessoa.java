package com.myevent.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.br.CPF;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@Entity
@Builder
@Audited
@NoArgsConstructor
@AllArgsConstructor
@Table(name = Pessoa.TABLE_NAME)
public class Pessoa {

    public static final String TABLE_NAME = "tb_pessoa";
    public static final String SEQ_NAME = "tb_pessoa_seq";

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    @Column(name = "id_pessoa", nullable = false)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @CPF
    @Column(name = "cpf", nullable = false, length = 14)
    private String cpf;


}
