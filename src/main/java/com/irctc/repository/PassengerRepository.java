package com.irctc.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.irctc.entity.Passenger;
public interface PassengerRepository extends JpaRepository<Passenger, Long> {}
