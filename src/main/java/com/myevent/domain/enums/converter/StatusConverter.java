package com.myevent.domain.enums.converter;

import com.myevent.domain.enums.StatusEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Objects;

@Converter
public class StatusConverter implements AttributeConverter<StatusEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(StatusEnum value) {
        return Objects.isNull(value) ? null : value.getValor();
    }

    @Override
    public StatusEnum convertToEntityAttribute(Integer integer) {
        return Objects.isNull(integer) ? null : StatusEnum.porValor(integer);
    }
}
