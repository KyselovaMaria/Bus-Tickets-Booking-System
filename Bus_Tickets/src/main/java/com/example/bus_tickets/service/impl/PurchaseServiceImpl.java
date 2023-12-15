package com.example.bus_tickets.service.impl;

import com.example.bus_tickets.model.PurchaseInfo;
import com.example.bus_tickets.model.Ticket;
import com.example.bus_tickets.model.User;
import com.example.bus_tickets.repository.PurchaseRepository;
import com.example.bus_tickets.service.PurchaseService;
import com.example.bus_tickets.service.TicketService;
import com.example.bus_tickets.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final TicketService ticketService;
    private final UserService userService;

    @Autowired
    public PurchaseServiceImpl(PurchaseRepository purchaseRepository, TicketService ticketService, UserService userService) {
        this.purchaseRepository = purchaseRepository;
        this.ticketService = ticketService;
        this.userService = userService;
    }

    @Override
    public User purchaseTicket(String userEmail, String userName, long ticketNumber, int quantity) {
        Optional<Ticket> optionalTicket = ticketService.getTicketById(ticketNumber);

        if (((Optional<?>) optionalTicket).isPresent()) {
            Ticket ticket = optionalTicket.get();
            double totalPrice = calculateTotalPrice(ticket, quantity, false);

            PurchaseInfo purchaseInfo = new PurchaseInfo();
            purchaseInfo.setPurchaseId(UUID.randomUUID().toString());
            purchaseInfo.setUserEmail(userEmail);
            purchaseInfo.setUserName(userName);
            purchaseInfo.setPurchasedTicket(ticket);
            purchaseInfo.setQuantity(quantity);
            purchaseInfo.setTotalPrice(totalPrice);

            User user = userService.getUserByEmail(userEmail);

            if (user != null) {
                user.getPurchases().add(purchaseInfo);
                userService.updateUser(user);
            }

            return user;
        } else {
            // Логіка для випадку, коли квиток з вказаним номером не знайдено
            return null;
        }
    }

    @Override
    public PurchaseInfo getPurchaseInfo(String purchaseId) {
        PurchaseInfo purchaseInfo = purchaseRepository.findById(purchaseId).orElse(null);

        if (purchaseInfo != null) {
            double totalPrice = calculateTotalPrice(purchaseInfo);
            purchaseInfo.setTotalPrice(totalPrice);
        }

        return purchaseInfo;
    }

    private double calculateTotalPrice(Ticket ticket, int quantity, boolean toIntermediate) {
        double price = toIntermediate ? ticket.getPriceToIntermediate() : ticket.getPriceToEnd();
        return price * quantity;
    }

    private double calculateTotalPrice(PurchaseInfo purchaseInfo) {
        return calculateTotalPrice(purchaseInfo.getPurchasedTicket(), purchaseInfo.getQuantity(), false);
    }
}
