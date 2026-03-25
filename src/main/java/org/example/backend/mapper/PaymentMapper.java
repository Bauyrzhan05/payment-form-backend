package org.example.backend.mapper;

import org.example.backend.dto.PaymentDto;
import org.example.backend.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",  uses = {CurrencyMapper.class})
public interface PaymentMapper {

    @Mapping(source = "currency", target = "currencyDto")
    @Mapping(source = "account", target = "accountId")
    PaymentDto toDto(Payment payment);
    @Mapping(source = "currencyDto", target = "currency")
    @Mapping(source = "accountId", target = "account")
    Payment toEntity(PaymentDto paymentDto);

    List<PaymentDto> toDtoList(List<Payment> payments);
}
