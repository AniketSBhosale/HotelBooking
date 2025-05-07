package com.example.demo.services;

import com.example.demo.model.HotelsModel;
import com.example.demo.repository.HotelsRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelsService {

    @Autowired
    private HotelsRepository hotelsRepository;

    // Get all hotels
    public List<HotelsModel> getAllHotels() {
        return hotelsRepository.getAllHotels();
    }

    // Get hotels by owner
    public List<HotelsModel> getHotelsByOwner(int ownerId) {
        return hotelsRepository.getHotelsByOwner(ownerId);
    }

    // Add a hotel
    public void addHotel(int ownerId, HotelsModel hotel) {
        hotelsRepository.addHotel(ownerId, hotel);
    }

    // Update a hotel
    public boolean updateHotel(int ownerId, int hotelId, HotelsModel hotel) {
        return hotelsRepository.updateHotel(ownerId, hotelId, hotel);
    }

    // Delete a hotel
    public boolean deleteHotel(int ownerId, int hotelId) {
        return hotelsRepository.deleteHotel(ownerId, hotelId);
    }
}
