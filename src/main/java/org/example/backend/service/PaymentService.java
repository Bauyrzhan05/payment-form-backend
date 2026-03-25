package org.example.backend.service;

import lombok.RequiredArgsConstructor;
import org.example.backend.dto.PaymentDto;
import org.example.backend.dto.PaymentStatsDto;
import org.example.backend.entity.Payment;
import org.example.backend.entity.Status;
import org.example.backend.entity.User;
import org.example.backend.mapper.PaymentMapper;
import org.example.backend.repo.PaymentRepo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepo paymentRepo;
    private final PaymentMapper paymentMapper;

    private User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        assert auth != null;
        return (User) auth.getPrincipal();
    }

    public List<PaymentDto> getAll() {
        return paymentMapper.toDtoList(paymentRepo.findAll());
    }

    public PaymentDto getOne(Long id) {
        return paymentMapper.toDto(paymentRepo.findById(id).orElseThrow());
    }

    public PaymentDto create(PaymentDto paymentDto) {

        Payment payment = paymentMapper.toEntity(paymentDto);
        payment.setAccount(getCurrentUser().getId());
        payment.setStatus(Status.Created);

        return paymentMapper.toDto(paymentRepo.save(payment));
    }

    public boolean delete(Long id) {
        paymentRepo.deleteById(id);
        return true;
    }

    public PaymentStatsDto getStats() {
        long totalCount = paymentRepo.count();
        Integer totalAmount = paymentRepo.findTotalAmount();
        if (totalAmount == null) totalAmount = 0;

        List<PaymentStatsDto.DailyStatsDto> dailyStats = paymentRepo.findDailyStats()
                .stream()
                .map(row -> new PaymentStatsDto.DailyStatsDto(
                        row[0].toString(),
                        ((Number) row[1]).longValue(),
                        ((Number) row[2]).intValue()
                ))
                .toList();

        return new PaymentStatsDto(totalCount, totalAmount, dailyStats);
    }

}
