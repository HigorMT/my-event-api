package com.myevent.domain.repository.criteria;


import com.myevent.api.dto.perfil.PerfilFilter;
import com.myevent.api.dto.perfil.PerfilResume;
import com.myevent.domain.entity.Perfil;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.hasText;

@Repository
public class PerfilCriteria  extends AbstractCriteria {

    public Page<PerfilResume> buscarPaginado(PerfilFilter filter, Pageable pageable) {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<PerfilResume> criteriaQuery = criteriaBuilder.createQuery(PerfilResume.class);
        Root<Perfil> root = criteriaQuery.from(Perfil.class);

        criteriaQuery.where(createPredicates(filter, criteriaBuilder, root));
        criteriaQuery.multiselect(
                root.get("id"),
                root.get("nome"),
                root.get("padrao"),
                root.get("descricao"),
                root.get("status")
        );
        criteriaQuery.orderBy(criteriaBuilder.asc(root.get("id")));

        TypedQuery<PerfilResume> typedQuery = this.entityManager.createQuery(criteriaQuery);

        this.configurePageable(typedQuery, pageable);

        return new PageImpl<>(typedQuery.getResultList(), pageable, total(filter));
    }

    private long total(PerfilFilter filter) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<Perfil> root = criteriaQuery.from(Perfil.class);

        criteriaQuery.where(createPredicates(filter, criteriaBuilder, root));
        criteriaQuery.select(criteriaBuilder.count(root));

        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    private Predicate[] createPredicates(PerfilFilter filter, CriteriaBuilder criteriaBuilder, Root<Perfil> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (hasText(filter.getNome())) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("nome")), "%" + filter.getNome() + "%"));
        }

        if (hasText(filter.getDescricao())) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("descricao")), "%" + filter.getDescricao() + "%"));
        }

        if (!isNull(filter.getStatus())) {
            predicates.add(criteriaBuilder.equal(criteriaBuilder.upper(root.get("status")), filter.getStatus()));
        }

        return predicates.toArray(new Predicate[0]);
    }
}
