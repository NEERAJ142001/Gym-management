// src/main/java/com/example/gym/repository/TrainerRepository.java
package com.example.gym.repository;

import com.example.gym.entity.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainerRepository extends JpaRepository<Trainer, Long> {
}
