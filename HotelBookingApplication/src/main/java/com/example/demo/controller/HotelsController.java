package com.example.demo.controller;

import com.example.demo.model.HotelsModel;
import com.example.demo.services.HotelsService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotels")
@CrossOrigin(origins = "http://localhost:5173")  // Allow requests from the React frontend (port 5173)
public class HotelsController {

    @Autowired
    private HotelsService hotelsService;

    // Get all hotels
    @GetMapping
    public ResponseEntity<List<HotelsModel>> getAllHotels() {
        List<HotelsModel> hotels = hotelsService.getAllHotels();
        return ResponseEntity.ok(hotels);
    }

    // Get hotels by owner
    @GetMapping("/owners/{ownerId}/hotels")
    public ResponseEntity<List<HotelsModel>> getHotelsByOwner(@PathVariable int ownerId) {
        List<HotelsModel> hotels = hotelsService.getHotelsByOwner(ownerId);
        return ResponseEntity.ok(hotels);
    }

    // Add a hotel
    @PostMapping("/owners/{ownerId}/hotels")
    public ResponseEntity<String> addHotel(@PathVariable int ownerId, @RequestBody HotelsModel hotel) {
        hotelsService.addHotel(ownerId, hotel);
        return ResponseEntity.ok("Hotel added successfully");
    }

    // Update hotel details
    @PutMapping("/owners/{ownerId}/hotels/{hotelId}")
    public ResponseEntity<String> updateHotel(@PathVariable int ownerId, @PathVariable int hotelId, @RequestBody HotelsModel hotel) {
        boolean updated = hotelsService.updateHotel(ownerId, hotelId, hotel);
        if (updated) {
            return ResponseEntity.ok("Hotel updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update hotel");
        }
    }

    // Delete a hotel
    @DeleteMapping("/owners/{ownerId}/hotels/{hotelId}")
    public ResponseEntity<String> deleteHotel(@PathVariable int ownerId, @PathVariable int hotelId) {
        boolean deleted = hotelsService.deleteHotel(ownerId, hotelId);
        if (deleted) {
            return ResponseEntity.ok("Hotel deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to delete hotel");
        }
    }
}
