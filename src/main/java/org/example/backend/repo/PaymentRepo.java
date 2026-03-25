package org.example.backend.repo;

import org.example.backend.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PaymentRepo extends JpaRepository<Payment, Long> {

    @Query("SELECT CAST(p.createAt AS date), COUNT(p), SUM(p.amount) " +
            "FROM Payment p GROUP BY CAST(p.createAt AS date) ORDER BY CAST(p.createAt AS date) DESC")
    List<Object[]> findDailyStats();

    @Query("SELECT SUM(p.amount) FROM Payment p")
    Integer findTotalAmount();
}
