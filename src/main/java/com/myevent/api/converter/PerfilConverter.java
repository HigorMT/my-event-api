package com.myevent.api.converter;

import com.myevent.api.dto.perfil.PerfilRequest;
import com.myevent.api.dto.perfil.PerfilResponse;
import com.myevent.domain.entity.Perfil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, uses = {PermissaoConverter.class})
public interface PerfilConverter {

    @Mapping(target = "permissoes", source = "permissoes")
    Perfil toEntity(PerfilRequest request);

    @Mapping(target = "permissoes", source = "permissoes")
    PerfilResponse toResponse(Perfil entity);

    @Mapping(target = "permissoes", source = "permissoes")
    List<PerfilResponse> toResponse(List<Perfil> entities);

}
