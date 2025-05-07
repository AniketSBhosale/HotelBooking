package com.example.demo.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.model.UsersModel;

@Repository
public class UsersRepository {

    @Autowired
    JdbcTemplate template;

    public int getRoleIdByName(String roleName) {
        String sql = "SELECT role_id FROM roles WHERE role_name = ?";
        try {
            return template.queryForObject(sql, new Object[]{roleName}, Integer.class);
        } catch (Exception e) {
            return -1;
        }
    }

    public boolean isAddNewUser(UsersModel user) {
        String sql = "INSERT INTO users (name, email, password, phone, role_id) VALUES (?, ?, ?, ?, ?)";
        int result = template.update(
            sql, 
            user.getName(), 
            user.getEmail(), 
            user.getPassword(), 
            user.getPhone(), 
            user.getRole_id()
        );
        return result > 0;
    }

    public List<UsersModel> getAllUsers() {
        String sql = "SELECT u.user_id, u.name, u.email, u.password, u.phone, u.role_id, u.created_at, r.role_name "
                   + "FROM users u JOIN roles r ON u.role_id = r.role_id";
        return template.query(sql, new RowMapper<UsersModel>() {
            @Override
            public UsersModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                UsersModel user = new UsersModel();
                user.setUser_id(rs.getInt("user_id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setPhone(rs.getString("phone"));
                user.setRole_id(rs.getInt("role_id"));
                user.setCreated_at(rs.getTimestamp("created_at"));
                user.setRole_name(rs.getString("role_name")); // Set role name
                return user;
            }
        });
    }

    public UsersModel findUserByEmailAndPassword(String email, String password) {
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
        try {
            return template.queryForObject(
                sql,
                new Object[]{email, password},
                new BeanPropertyRowMapper<>(UsersModel.class)
            );
        } catch (Exception e) {
            return null;
        }
    }

    public String findRoleNameByRoleId(int roleId) {
        String sql = "SELECT role_name FROM roles WHERE role_id = ?";
        try {
            return template.queryForObject(sql, new Object[]{roleId}, String.class);
        } catch (Exception e) {
            return null;
        }
    }

    public String getUserRole(String email) {
        String sql = "SELECT r.role_name FROM users u JOIN roles r ON u.role_id = r.role_id WHERE u.email = ?";
        try {
            return template.queryForObject(sql, new Object[]{email}, String.class);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean validateUser(String email, String password) {
        String sql = "SELECT COUNT(*) FROM users WHERE email = ? AND password = ?";
        int count = template.queryForObject(sql, new Object[]{email, password}, Integer.class);
        return count == 1;
    }
}
