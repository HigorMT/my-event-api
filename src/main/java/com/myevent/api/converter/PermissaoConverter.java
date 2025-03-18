package com.myevent.api.converter;

import com.myevent.api.dto.permissao.PermissaoRequest;
import com.myevent.api.dto.permissao.PermissaoResponse;
import com.myevent.domain.entity.Permissao;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface PermissaoConverter {

    Permissao toEntity(PermissaoRequest request);

    PermissaoResponse toResponse(Permissao entity);

    List<Permissao> toEntity(List<PermissaoRequest> entities);

    List<PermissaoResponse> toResponse(List<Permissao> entities);

}
