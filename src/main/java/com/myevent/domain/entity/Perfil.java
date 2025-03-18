package com.myevent.domain.entity;

import com.myevent.domain.enums.StatusEnum;
import com.myevent.domain.enums.converter.StatusConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.AuditJoinTable;
import org.hibernate.envers.Audited;

import java.util.List;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@Entity
@Builder
@Audited
@NoArgsConstructor
@AllArgsConstructor
@Table(name = Perfil.TABLE_NAME)
public class Perfil {

    public static final String TABLE_NAME = "tb_perfil";
    public static final String SEQ_NAME = "tb_perfil_seq";

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    @Column(name = "id_perfil", nullable = false)
    private Long id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Default
    @Column(name = "padrao", nullable = false)
    private boolean padrao = false;

    @Column(name = "status")
    @Convert(converter = StatusConverter.class)
    private StatusEnum status;

    @OneToMany
    @AuditJoinTable
    @JoinTable(
            name = "tb_perfil_permissao",
            joinColumns = {@JoinColumn(name = "id_perfil")},
            inverseJoinColumns = {@JoinColumn(name = "id_permissao")}
    )
    private List<Permissao> permissoes;

}
