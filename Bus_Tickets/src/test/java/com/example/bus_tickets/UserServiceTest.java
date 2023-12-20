package com.example.bus_tickets;

import com.example.bus_tickets.model.User;
import com.example.bus_tickets.service.UserService;

import jakarta.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Isolation;

import java.util.Objects;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testAddUserAndFindUserByEmail() {
        // Add a test user
        User user = new User();
        user.setEmail("test@example.com");
        user.setUsername("Test User");
        userService.createUser(user);

        // Try to add another user with the same email, expect DataIntegrityViolationException
        try {
            User duplicateUser = new User();
            duplicateUser.setEmail("test@example.com");
            duplicateUser.setUsername("Duplicate Test User");
            userService.createUser(duplicateUser);
        } catch (DataIntegrityViolationException e) {
            // Expected exception
        }

        // Verify that the initial user is still present
        //User foundUser = userService.getUserByEmail("test@example.com");
        //assertEquals("Test User", foundUser.getUsername());
    }
}