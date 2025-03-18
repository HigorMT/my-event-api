package com.myevent.domain.repository.criteria;

import com.myevent.api.dto.usuario.UsuarioFilter;
import com.myevent.api.dto.usuario.UsuarioResume;
import com.myevent.domain.entity.Usuario;
import io.jsonwebtoken.lang.Strings;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static io.jsonwebtoken.lang.Strings.hasText;
import static java.util.Objects.isNull;

@Repository
public class UsuarioCriteria extends AbstractCriteria {

    public Page<UsuarioResume> buscarPaginado(UsuarioFilter filter, Pageable pageable) {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<UsuarioResume> criteriaQuery = criteriaBuilder.createQuery(UsuarioResume.class);
        Root<Usuario> root = criteriaQuery.from(Usuario.class);

        criteriaQuery.where(createPredicates(filter, criteriaBuilder, root));
        criteriaQuery.multiselect(
                root.get("id"),
                root.get("nome"),
                root.get("email"),
                root.get("status"),
                root.get("credencial")
        );
        criteriaQuery.orderBy(criteriaBuilder.asc(root.get("id")));

        TypedQuery<UsuarioResume> typedQuery = this.entityManager.createQuery(criteriaQuery);

        this.configurePageable(typedQuery, pageable);

        return new PageImpl<>(typedQuery.getResultList(), pageable, total(filter));
    }

    private long total(UsuarioFilter filter) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<Usuario> root = criteriaQuery.from(Usuario.class);

        criteriaQuery.where(createPredicates(filter, criteriaBuilder, root));
        criteriaQuery.select(criteriaBuilder.count(root));

        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    private Predicate[] createPredicates(UsuarioFilter filter, CriteriaBuilder criteriaBuilder, Root<Usuario> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (hasText(filter.getNome())) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("nome")), "%" + filter.getNome() + "%"));
        }

        if (hasText(filter.getCredencial())) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("credencial")), "%" + filter.getCredencial() + "%"));
        }

        if (!isNull(filter.getStatus())) {
            predicates.add(criteriaBuilder.equal(criteriaBuilder.upper(root.get("status")), filter.getStatus()));
        }

        return predicates.toArray(new Predicate[0]);
    }
    
}
