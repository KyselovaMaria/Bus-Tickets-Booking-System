package com.example.bus_tickets.service;

import com.example.bus_tickets.model.PurchaseInfo;
import com.example.bus_tickets.model.User;

public interface PurchaseService {
    User purchaseTicket(String userEmail, String userName, long ticketNumber, int quantity);
    PurchaseInfo getPurchaseInfo(String purchaseId);
}
