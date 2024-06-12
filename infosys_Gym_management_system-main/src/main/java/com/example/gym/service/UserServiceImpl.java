package com.example.gym.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.gym.entity.Userdetails;
import com.example.gym.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public Userdetails saveUser(Userdetails user) {
        return userRepo.save(user);
    }

    @Override
    public boolean checkEmail(String email) {
        return userRepo.existsByEmail(email);
    }

    @Override
    public void removeSession() {
        try {
            HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
            session.removeAttribute("message");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean validateUser(String email, String password) {
        Userdetails user = userRepo.findByEmail(email);
        return user != null && user.getPassword().equals(password);
    }

    @Override
    public Userdetails getUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }
}
