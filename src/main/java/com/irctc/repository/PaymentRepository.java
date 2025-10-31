package com.irctc.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.irctc.entity.Payment;
public interface PaymentRepository extends JpaRepository<Payment, Long> {}
