package com.example.gym.service;

import com.example.gym.entity.Userdetails;

public interface UserService {
    Userdetails saveUser(Userdetails user);

    boolean checkEmail(String email);

    void removeSession();



	boolean validateUser(String email, String password);

	

	Userdetails getUserByEmail(String email);
}
