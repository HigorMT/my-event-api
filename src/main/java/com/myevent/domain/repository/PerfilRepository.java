package com.myevent.domain.repository;

import com.myevent.api.dto.perfil.PerfilResume;
import com.myevent.domain.entity.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerfilRepository extends RevisionRepository<Perfil,Long, Long>, JpaRepository<Perfil, Long> {

    @Query("SELECT (CASE WHEN(COUNT(p.id) > 0) THEN TRUE ELSE FALSE END) FROM Perfil p " +
            "   WHERE (:id IS NULL OR p.id <> :id)" +
            "     AND LOWER(TRIM(p.nome)) = LOWER(TRIM(:nome))")
    boolean validarDuplicidade(Long id, String nome);

    @Query("SELECT NEW com.myevent.api.dto.perfil.PerfilResume(p.id, p.nome, p.padrao, p.descricao, p.status) FROM Perfil p " +
            "   WHERE p.status = com.myevent.domain.enums.StatusEnum.ATIVO")
    List<PerfilResume> findAllActives();

}
