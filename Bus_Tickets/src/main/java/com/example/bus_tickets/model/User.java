package com.example.bus_tickets.model;

import jakarta.persistence. *;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    @Id
    private String email;

    private String username;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userEmail")
    private List<PurchaseInfo> purchases;

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

}
