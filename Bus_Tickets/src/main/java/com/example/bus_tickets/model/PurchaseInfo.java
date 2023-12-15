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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long purchaseId;

    @ManyToOne
    @JoinColumn(name = "user_email")
    private User userEmail;

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket purchasedTicket;

    private int quantity;
    private double totalPrice;

    // getters and setters

    // constructor
    public PurchaseInfo(User userEmail, Ticket purchasedTicket, int quantity, double totalPrice) {
        this.userEmail = userEmail;
        this.purchasedTicket = purchasedTicket;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }
}
