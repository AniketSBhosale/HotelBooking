package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.OwnerModel;
import com.example.demo.services.OwnerService;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class OwnerController {

    @Autowired
    private OwnerService service;

    @PostMapping("/addOwner")
    public String registerOwner(@RequestBody OwnerModel owner) {
        return service.registerOwner(owner);
    }
}
