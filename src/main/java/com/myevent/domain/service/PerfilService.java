package com.myevent.domain.service;

import com.myevent.api.dto.perfil.PerfilFilter;
import com.myevent.api.dto.perfil.PerfilResume;
import com.myevent.common.exception.BusinessException;
import com.myevent.common.exception.DataNotFoundException;
import com.myevent.domain.entity.Perfil;
import com.myevent.domain.repository.PerfilRepository;
import com.myevent.domain.repository.criteria.PerfilCriteria;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.myevent.domain.enums.StatusEnum.ATIVO;
import static com.myevent.domain.enums.StatusEnum.INATIVO;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class PerfilService {

    private final PerfilRepository repository;
    private final PerfilCriteria criteria;

    public Perfil findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new DataNotFoundException(format("Não encontrado nenhum perfil com id: %d", id) ));
    }

    public Page<PerfilResume> pageable(PerfilFilter filter, Pageable pageable) {
        return criteria.buscarPaginado(filter, pageable);
    }

    @Transactional
    public Perfil save(Perfil entity) {
        validation(entity);
        return repository.save(entity);
    }

    @Transactional
    public Perfil update(Perfil entity, Long id) {
        Perfil dataBase = findById(id);
        entity.setId(id);

        return save(entity);
    }

    @Transactional
    public Perfil ativar(Long id) {
        Perfil dataBase = findById(id);
        dataBase.setStatus(ATIVO);

        return repository.save(dataBase);
    }

    @Transactional
    public Perfil inativar(Long id) {
        Perfil dataBase = findById(id);
        dataBase.setStatus(INATIVO);

        return repository.save(dataBase);
    }

    private void validation(Perfil entity) {
        boolean duplicado = repository.validarDuplicidade(entity.getId(), entity.getNome());

        if (duplicado) {
            throw new BusinessException("Já existe outro Perfil com o mesmo nome registrado no sistema.");
        }

    }

    public List<PerfilResume> findAllActives() {
        return repository.findAllActives();
    }
}
