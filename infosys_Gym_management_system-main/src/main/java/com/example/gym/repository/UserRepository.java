package com.example.gym.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gym.entity.Userdetails;

@Repository
public interface UserRepository extends JpaRepository<Userdetails, Long> {
    boolean existsByEmail(String email);
    Userdetails findByUsername(String username);
    Userdetails findByEmail(String email);
}
