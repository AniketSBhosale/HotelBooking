package com.example.demo.model;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class HotelsModel {
    private int hotel_id;
    private int owner_id;
    private String name;
    private String location;
    private String category;
    private Timestamp created_at; // Optional: will be auto-handled by DB if set as DEFAULT CURRENT_TIMESTAMP
}
