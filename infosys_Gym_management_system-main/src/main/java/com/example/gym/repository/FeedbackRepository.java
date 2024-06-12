package com.example.gym.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gym.entity.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}
