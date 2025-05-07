package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repository.LoginRepository;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    public String validateLogin(String email, String password) {
        Integer userId = loginRepository.getUserIdByEmailAndPassword(email, password);
        if (userId == null) {
            return "Invalid";
        }
        String roleName = loginRepository.getRoleNameByUserId(userId);
        return roleName; 
    }
}