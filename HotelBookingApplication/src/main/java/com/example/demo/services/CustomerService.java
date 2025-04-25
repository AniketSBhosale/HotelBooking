package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.CustomerModel;
import com.example.demo.repository.CustomerRepository;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository repo;

    public String registerCustomer(CustomerModel customer) {
        int roleId = repo.getRoleIdByName("Customer");
        int userId = repo.registerCustomer(customer, roleId);
        return "Customer registered successfully with user_id: " + userId;
    }
}
