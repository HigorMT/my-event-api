package com.myevent.domain.entity;

import com.myevent.domain.enums.PermissaoEnum;
import com.myevent.domain.enums.converter.PermissoEnumConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
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

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@Entity
@Builder
@Audited
@NoArgsConstructor
@AllArgsConstructor
@Table(name = Permissao.TABLE_NAME)
public class Permissao {

    public static final String TABLE_NAME = "tb_permissao";
    public static final String SEQ_NAME = "tb_permissao_seq";

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    @Column(name = "id_permissao", nullable = false)
    private Long id;

    @Column(name = "permissao", nullable = false)
    @Convert(converter = PermissoEnumConverter.class)
    private PermissaoEnum permissao;

    @Column(name = "descricao", nullable = false)
    private String descricao;

}