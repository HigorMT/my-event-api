package com.myevent.domain.enums.converter;

import com.myevent.domain.enums.StatusEventoEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Objects;

@Converter
public class StatusEventoConverter implements AttributeConverter<StatusEventoEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(StatusEventoEnum value) {
        return Objects.isNull(value) ? null : value.getValor();
    }

    @Override
    public StatusEventoEnum convertToEntityAttribute(Integer integer) {
        return Objects.isNull(integer) ? null : StatusEventoEnum.porValor(integer);
    }

}
