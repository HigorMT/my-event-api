package com.myevent.api.converter;

import com.myevent.api.dto.usuario.UsuarioRegisterRequest;
import com.myevent.api.dto.usuario.UsuarioRequest;
import com.myevent.api.dto.usuario.UsuarioResponse;
import com.myevent.domain.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;


@Mapper(componentModel = SPRING, uses = {PerfilConverter.class})
public interface UsuarioConverter {

    @Mappings({
            @Mapping(target = "perfil.id", source = "perfilId"),
            @Mapping(target = "dataAtualizacao", ignore = true),
            @Mapping(target = "dataCadastro", ignore = true),
            @Mapping(target = "ultimoLogin", ignore = true),
    })
    Usuario toEntity(UsuarioRegisterRequest request);

    @Mappings({
            @Mapping(target = "perfil.id", source = "perfilId"),
            @Mapping(target = "dataAtualizacao", ignore = true),
            @Mapping(target = "dataCadastro", ignore = true),
            @Mapping(target = "ultimoLogin", ignore = true),
    })
    Usuario toEntity(UsuarioRequest request);

    @Mappings({
            @Mapping(target = "perfil", source = "perfil"),
    })
    UsuarioResponse toResponse(Usuario entity);

    @Mappings({
            @Mapping(target = "perfil", source = "perfil"),
    })
    List<UsuarioResponse> toResponse(List<Usuario> entities);

}
