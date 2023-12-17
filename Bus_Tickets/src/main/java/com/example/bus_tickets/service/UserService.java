package com.example.bus_tickets.service;

import com.example.bus_tickets.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    User createUser(User user);
    User getUserByEmail(String email);
    List<User> getAllUsers();
    User updateUser(User user);
    void deleteUser(String email);
}
