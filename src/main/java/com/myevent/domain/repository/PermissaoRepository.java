package com.myevent.domain.repository;

import com.myevent.domain.entity.Permissao;
import com.myevent.domain.enums.PermissaoEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissaoRepository extends RevisionRepository<Permissao,Long, Long>, JpaRepository<Permissao, Long> {

    Optional<Permissao> findByPermissao(PermissaoEnum permissao);

}