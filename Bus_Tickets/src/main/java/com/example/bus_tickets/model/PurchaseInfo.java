package com.example.bus_tickets.model;

import lombok.*;

import jakarta.persistence. *;


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
    private User user;

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket purchasedTicket;

    private int quantity;
    private double totalPrice;

    public PurchaseInfo(User user, Ticket purchasedTicket, int quantity, double totalPrice) {
        this.user = user;
        this.purchasedTicket = purchasedTicket;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public String getUserEmail() {
        return user != null ? user.getEmail() : null;
    }
}
