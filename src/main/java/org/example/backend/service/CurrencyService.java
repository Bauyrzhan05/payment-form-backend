package org.example.backend.service;

import lombok.RequiredArgsConstructor;
import org.example.backend.dto.CurrencyDto;
import org.example.backend.mapper.CurrencyMapper;
import org.example.backend.repo.CurrencyRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyService {

    private final CurrencyRepo currencyRepo;
    private final CurrencyMapper currencyMapper;

    public List<CurrencyDto> getAll() {
        return currencyMapper.toDtoList(currencyRepo.findAll());
    }

    public CurrencyDto getOne(Long id) {
        return currencyMapper.toDto(currencyRepo.findById(id).orElseThrow());
    }

    public CurrencyDto create(CurrencyDto currencyDto) {
        return currencyMapper.toDto(currencyRepo.save(currencyMapper.toEntity(currencyDto)));
    }

    public Boolean delete(Long id) {
        currencyRepo.deleteById(id);
        return true;
    }
}
