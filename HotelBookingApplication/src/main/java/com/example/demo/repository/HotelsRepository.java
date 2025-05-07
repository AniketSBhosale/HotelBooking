package com.example.demo.repository;

import com.example.demo.model.HotelsModel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class HotelsRepository {

    @Autowired
    private JdbcTemplate template;

    // Get all hotels
    public List<HotelsModel> getAllHotels() {
        String sql = "SELECT * FROM Hotels";
        return template.query(sql, (rs, rowNum) -> {
            HotelsModel h = new HotelsModel();
            h.setHotel_id(rs.getInt("hotel_id"));
            h.setOwner_id(rs.getInt("owner_id"));
            h.setName(rs.getString("name"));
            h.setLocation(rs.getString("location"));
            h.setCategory(rs.getString("category"));
            h.setCreated_at(rs.getTimestamp("created_at"));
            return h;
        });
    }

    // Get hotels by owner
    public List<HotelsModel> getHotelsByOwner(int ownerId) {
        String sql = "SELECT * FROM Hotels WHERE owner_id = ?";
        return template.query(sql, new Object[] { ownerId }, (rs, rowNum) -> {
            HotelsModel h = new HotelsModel();
            h.setHotel_id(rs.getInt("hotel_id"));
            h.setOwner_id(rs.getInt("owner_id"));
            h.setName(rs.getString("name"));
            h.setLocation(rs.getString("location"));
            h.setCategory(rs.getString("category"));
            h.setCreated_at(rs.getTimestamp("created_at"));
            return h;
        });
    }

    // Add a hotel
    public void addHotel(int ownerId, HotelsModel hotel) {
        String sql = "INSERT INTO Hotels (owner_id, name, location, category) VALUES (?, ?, ?, ?)";
        template.update(sql, ownerId, hotel.getName(), hotel.getLocation(), hotel.getCategory());
    }

    // Update a hotel
    public boolean updateHotel(int ownerId, int hotelId, HotelsModel hotel) {
        String sql = "UPDATE Hotels SET name = ?, location = ?, category = ? WHERE hotel_id = ? AND owner_id = ?";
        int rowsAffected = template.update(sql, hotel.getName(), hotel.getLocation(), hotel.getCategory(), hotelId, ownerId);
        return rowsAffected > 0;
    }

    // Delete a hotel
    public boolean deleteHotel(int ownerId, int hotelId) {
        String sql = "DELETE FROM Hotels WHERE hotel_id = ? AND owner_id = ?";
        int rowsAffected = template.update(sql, hotelId, ownerId);
        return rowsAffected > 0;
    }
}
