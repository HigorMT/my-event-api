package com.myevent.domain.entity.audit;

import com.myevent.common.listener.EntityRevisionListener;
import com.myevent.domain.entity.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;
import org.springframework.data.history.RevisionMetadata.RevisionType;

import java.time.LocalDateTime;

import static jakarta.persistence.EnumType.STRING;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = AuditRev.TABLE_NAME)
@RevisionEntity(value = EntityRevisionListener.class)
public class AuditRev {

    public static final String TABLE_NAME = "revinfo";
    public static final String SEQUENCE_NAME = "tb_revinfo_seq";

    @Id
    @RevisionNumber
    @Column(name = "rev", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
    private Long id;

    @Column(name = "ip")
    private String ip;

    @Enumerated(STRING)
    @Column(name = "rev_type")
    private RevisionType tipoRevisao;

    @Column(name = "entidade")
    private String entidade;

    @RevisionTimestamp
    @Column(name = "data_cadastro")
    private LocalDateTime dtCadastro;

    @OneToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @Column(name = "is_device")
    private boolean deviceReq = false;

    @Column(name = "is_system")
    private boolean system = false;

}
