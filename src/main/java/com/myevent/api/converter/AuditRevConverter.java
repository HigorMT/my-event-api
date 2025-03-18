package com.myevent.api.converter;

import com.myevent.api.dto.audit.AuditRevResponse;
import com.myevent.domain.entity.audit.AuditRev;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, uses = {UsuarioConverter.class})
public interface AuditRevConverter {

    @Mapping(target = "usuario", source = "usuario")
    AuditRevResponse toResponse(AuditRev entity);

    @Mapping(target = "usuario", source = "usuario")
    List<AuditRevResponse> toResponse(List<AuditRev> entities);

}
