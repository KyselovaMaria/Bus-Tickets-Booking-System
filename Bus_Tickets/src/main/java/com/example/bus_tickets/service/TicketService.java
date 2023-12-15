package com.example.bus_tickets.service;

import com.example.bus_tickets.model.Ticket;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface TicketService {
    Ticket createTicket(Ticket ticket);
    Optional<Ticket> getTicketById(long id);
    Ticket updateTicket(Ticket ticket);
    void deleteTicket(long id);

    List<Ticket> getTicketsByDestination(String destination);
    List<Ticket> getTicketsByIntermediate(String intermediate);
    List<Ticket> getTicketsByNumber(String number);
    List<Ticket> getAllTickets();

    List<Ticket> searchTickets(String destination, LocalDate date);
}
