package com.example.demo.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.OwnerModel;
import com.example.demo.repository.OwnerRepository;

@Service
public class OwnerService {
    @Autowired
    private OwnerRepository repo;

    public String registerOwner(OwnerModel owner) {
        int roleId = repo.getRoleIdByName("owner");
        int userId = repo.registerOwner(owner, roleId);
        return "Owner registered successfully with user_id: " + userId;
    }
}

