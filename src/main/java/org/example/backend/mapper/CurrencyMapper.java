package org.example.backend.mapper;

import org.example.backend.dto.CurrencyDto;
import org.example.backend.entity.Currency;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CurrencyMapper {
    CurrencyDto toDto(Currency currency);
    Currency toEntity(CurrencyDto currencyDto);

    List<CurrencyDto> toDtoList(List<Currency> currencies);

}
