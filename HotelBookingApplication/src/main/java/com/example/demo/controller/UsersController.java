package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.UsersModel;
import com.example.demo.services.UsersService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class UsersController {

    @Autowired
    UsersService service;

    @PostMapping("/addUsers")
    public ResponseEntity<Map<String, String>> register(@RequestBody UsersModel user) {
        Map<String, String> response = new HashMap<>();
        boolean added = service.isAddNewUser(user);
        if (added) {
            response.put("message", "User Registered Successfully");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Registration Failed: Invalid Role or Data");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/getallusers")
    public List<UsersModel> getAllUsers() {
        return service.getAllUsers();
    }

    @PostMapping("/loginUser")
    public Map<String, Object> login(@RequestBody UsersModel user) {
        Map<String, Object> response = new HashMap<>();
        UsersModel loggedInUser = service.findUserByEmailAndPassword(
            user.getEmail(), user.getPassword()
        );
        if (loggedInUser != null) {
            String roleName = service.getRoleName(loggedInUser.getRole_id());
            response.put("role", roleName);
            response.put("userId", loggedInUser.getUser_id());
        } else {
            response.put("role", "login failed invalid credentials");
        }
        return response;
    }
}
