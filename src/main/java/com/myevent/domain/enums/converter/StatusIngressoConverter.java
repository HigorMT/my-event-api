package com.myevent.domain.enums.converter;

import com.myevent.domain.enums.StatusIngressoEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Objects;

@Converter
public class StatusIngressoConverter implements AttributeConverter<StatusIngressoEnum, Integer> {


    @Override
    public Integer convertToDatabaseColumn(StatusIngressoEnum value) {
        return Objects.isNull(value) ? null : value.getValor();
    }

    @Override
    public StatusIngressoEnum convertToEntityAttribute(Integer integer) {
        return Objects.isNull(integer) ? null : StatusIngressoEnum.porValor(integer);
    }
}
