package com.myevent.domain.repository;

import com.myevent.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends RevisionRepository<Usuario,Long, Long>, JpaRepository<Usuario, Long> {

    @Query("SELECT u FROM Usuario u WHERE u.credencial = :userName")
    Optional<Usuario> findByUserName(String userName);

    @Query("SELECT (CASE WHEN(COUNT(u.id) > 0) THEN TRUE ELSE FALSE END ) FROM Usuario u " +
            "   WHERE (:id IS NULL OR u.id <> :id)" +
            "     AND (:credencial IS NOT NULL AND TRIM(LOWER(u.credencial)) = TRIM(LOWER(:credencial)))")
    boolean verificaDuplicidadeCredencial(String credencial, Long id);

    @Query("SELECT (CASE WHEN(COUNT(u.id) > 0) THEN TRUE ELSE FALSE END ) FROM Usuario u " +
            "   WHERE (:id IS NULL OR u.id <> :id)" +
            "     AND (:email IS NOT NULL AND TRIM(LOWER(u.email)) = TRIM(LOWER(:email)))")
    boolean verificaDuplicidadeEmail(String email, Long id);

}