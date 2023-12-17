package com.example.bus_tickets.service.impl;

import com.example.bus_tickets.model.*;
import com.example.bus_tickets.service.*;
import com.example.bus_tickets.repository.PurchaseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

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

        if (optionalTicket.isPresent()) {
            Ticket ticket = optionalTicket.get();
            double totalPrice = calculateTotalPrice(ticket, quantity, false);

            User user = userService.getUserByEmail(userEmail);

            if (user != null) {
                PurchaseInfo purchaseInfo = new PurchaseInfo();
                purchaseInfo.setUser(user);
                purchaseInfo.setPurchasedTicket(ticket);
                purchaseInfo.setQuantity(quantity);
                purchaseInfo.setTotalPrice(totalPrice);

                if (user.getPurchases() == null) {
                    user.setPurchases(new ArrayList<>());
                }

                user.getPurchases().add(purchaseInfo);
                userService.updateUser(user);

                return user;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }


   // @Override
   // public PurchaseInfo getPurchaseInfo(String purchaseId) {
   //     PurchaseInfo purchaseInfo = purchaseRepository.findById(purchaseId).orElse(null);
//
     //   if (purchaseInfo != null) {
     //       double totalPrice = calculateTotalPrice(purchaseInfo);
     //       purchaseInfo.setTotalPrice(totalPrice);
     //   }
//
     //   return purchaseInfo;
    //}

    @Override
    public PurchaseInfo getPurchaseInfo(Long purchaseId) {
         return purchaseRepository.findById(purchaseId).orElse(null);
    }
    private double calculateTotalPrice(Ticket ticket, int quantity, boolean toIntermediate) {
        double price = toIntermediate ? ticket.getPriceToIntermediate() : ticket.getPriceToEnd();
        return price * quantity;
    }

    private double calculateTotalPrice(PurchaseInfo purchaseInfo) {
        return calculateTotalPrice(purchaseInfo.getPurchasedTicket(), purchaseInfo.getQuantity(), false);
    }
}
