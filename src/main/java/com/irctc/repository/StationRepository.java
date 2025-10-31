package com.irctc.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.irctc.entity.Station;
public interface StationRepository extends JpaRepository<Station, Long> {}
