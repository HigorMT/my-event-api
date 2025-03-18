package com.myevent.domain.enums.converter;

import com.myevent.domain.enums.PermissaoEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Objects;

@Converter
public class PermissoEnumConverter implements AttributeConverter<PermissaoEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(PermissaoEnum value) {
        return Objects.isNull(value) ? null : value.getValor();
    }

    @Override
    public PermissaoEnum convertToEntityAttribute(Integer integer) {
        return Objects.isNull(integer) ? null : PermissaoEnum.porValor(integer);
    }

}
