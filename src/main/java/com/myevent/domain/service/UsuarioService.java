package com.myevent.domain.service;

import com.myevent.api.dto.usuario.UsuarioFilter;
import com.myevent.api.dto.usuario.UsuarioResume;
import com.myevent.common.exception.BusinessException;
import com.myevent.common.exception.DataNotFoundException;
import com.myevent.domain.entity.Perfil;
import com.myevent.domain.entity.Usuario;
import com.myevent.domain.repository.UsuarioRepository;
import com.myevent.domain.repository.criteria.UsuarioCriteria;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.myevent.domain.enums.StatusEnum.ATIVO;
import static com.myevent.domain.enums.StatusEnum.INATIVO;
import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository repository;
    private final PerfilService perfilService;
    private final UsuarioCriteria criteria;

    public Usuario findByUserNameOrNull(String userName) {
        return repository.findByUserName(userName).orElse(null);
    }

    public Usuario findByUserName(String userName) {
        return repository.findByUserName(userName).orElseThrow(() -> new DataNotFoundException("Usuário não encontrado."));
    }

    public Usuario findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new DataNotFoundException("Usuário não encontrado."));
    }

    public Page<UsuarioResume> pageable(UsuarioFilter filter, Pageable pageable) {
        return criteria.buscarPaginado(filter, pageable);
    }

    @Transactional
    public Usuario save(Usuario entity) {
        validation(entity);

        String criptPass = passwordEncoder.encode(entity.getSenha());
        setperfil(entity, null);
        entity.setSenha(criptPass);

        return repository.save(entity);
    }

    @Transactional
    public Usuario update(Usuario entity, Long id) {
        Usuario dataBase = findById(id);

        validation(entity);
        setperfil(entity, dataBase);
        entity.setSenha(dataBase.getSenha());

        return repository.save(entity);
    }

    private void setperfil(Usuario entity, Usuario dataBase) {
        Long perfilId = entity.getPerfil().getId();

        if (isNull(dataBase) || !perfilId.equals(dataBase.getPerfil().getId())) {
            Perfil perfil = perfilService.findById(perfilId);
            entity.setPerfil(perfil);
        }
    }

    @Transactional
    public Usuario ativar(Long id) {
        Usuario dataBase = findById(id);
        dataBase.setStatus(ATIVO);

        return repository.save(dataBase);
    }

    @Transactional
    public Usuario inativar(Long id) {
        Usuario dataBase = findById(id);
        dataBase.setStatus(INATIVO);

        return repository.save(dataBase);
    }

    private void validation(Usuario entity) {

        boolean verificaDuplicidade = repository.verificaDuplicidadeCredencial(entity.getCredencial(), entity.getId());

        if (verificaDuplicidade)
            throw new BusinessException("Existe outro Usuário cadastrado no sistema com a mesma credencial.");

        verificaDuplicidade = repository.verificaDuplicidadeEmail(entity.getEmail(), entity.getId());

        if (verificaDuplicidade)
            throw new BusinessException("Existe outro Usuário cadastrado no sistema com o mesmo e-mail.");

    }


}
