package com.example.gym.repository;

import com.example.gym.entity.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface SlotRepository extends JpaRepository<Slot, Long> {
    Optional<Slot> findByDateAndTiming(Date date, String timing);
}
