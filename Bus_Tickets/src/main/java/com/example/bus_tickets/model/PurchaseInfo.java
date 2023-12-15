package com.example.bus_tickets.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import jakarta.persistence. *;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PurchaseInfo {

    @Id
    private String purchaseId;

    private String userEmail;
    private String userName;

    @ManyToOne
    @JoinColumn(name = "user_email")
    private User user;

    @OneToOne
    @JoinColumn(name = "ticket_id")
    private Ticket purchasedTicket;

    private int quantity;
    private double totalPrice;

    // getters and setters

    // constructor
    public PurchaseInfo(String userEmail, String userName, User user, Ticket purchasedTicket, int quantity, double totalPrice) {
        this.purchaseId = UUID.randomUUID().toString();
        this.userEmail = userEmail;
        this.userName = userName;
        this.user = user;
        this.purchasedTicket = purchasedTicket;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }
}
