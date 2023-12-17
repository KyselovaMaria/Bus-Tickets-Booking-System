package com.example.bus_tickets.service;

import com.example.bus_tickets.model.PurchaseInfo;
import com.example.bus_tickets.model.User;
import org.springframework.stereotype.Service;


public interface PurchaseService {
    User purchaseTicket(String userEmail, String userName, long ticketNumber, int quantity);
   // PurchaseInfo getPurchaseInfo(String purchaseId);
    PurchaseInfo getPurchaseInfo(Long purchaseId);


}
