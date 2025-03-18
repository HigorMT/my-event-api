package com.myevent.domain.entity;

import com.myevent.domain.enums.StatusEnum;
import com.myevent.domain.enums.converter.StatusConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@Entity
@Builder
@Audited
@NoArgsConstructor
@AllArgsConstructor
@Table(name = Usuario.TABLE_NAME)
public class Usuario {

    public static final String TABLE_NAME = "tb_usuario";
    public static final String SEQ_NAME = "tb_usuario_seq";

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    @Column(name = "id_usuario", nullable = false)
    private Long id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "credencial", length = 50)
    private String credencial;

    @Column(name = "senha", nullable = false)
    private String senha;

    @Column(name = "ultimo_login")
    private LocalDateTime ultimoLogin;

    @Column(name = "email", length = 45)
    private String email;

    @Column(name = "status")
    @Convert(converter = StatusConverter.class)
    private StatusEnum status;

    @Column(name = "data_cadastro", nullable = false, updatable = false)
    private LocalDateTime dataCadastro;

    @Column(name = "data_atualizacao", nullable = false)
    private LocalDateTime dataAtualizacao;

    @OneToOne
    @JoinColumn(name = "id_perfil")
    private Perfil perfil;

    //<editor-fold desc="Callback" defaultstate="collapsed">

    @PrePersist
    void prePersist() {
        this.dataCadastro = LocalDateTime.now();
        this.dataAtualizacao = LocalDateTime.now();
    }

    @PreUpdate
    void preUpdate() {
        this.dataAtualizacao = LocalDateTime.now();
    }

    //</editor-fold>

}