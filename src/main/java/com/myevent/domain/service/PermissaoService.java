package com.myevent.domain.service;

import com.myevent.domain.entity.Permissao;
import com.myevent.domain.enums.PermissaoEnum;
import com.myevent.domain.repository.PermissaoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PermissaoService {

    private  final PermissaoRepository repository;

    public Permissao findByPermOrNull(PermissaoEnum permissao) {
        return repository.findByPermissao(permissao).orElse(null);
    }

    @Transactional
    public Permissao save(Permissao entity) {
        return repository.save(entity);
    }

    public List<Permissao> selectAll() {
        return repository.findAll();
    }
}
