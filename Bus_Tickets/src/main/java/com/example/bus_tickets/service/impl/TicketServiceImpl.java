package com.example.bus_tickets.service.impl;

import com.example.bus_tickets.model.Ticket;
import com.example.bus_tickets.repository.TicketRepository;
import com.example.bus_tickets.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public Optional<Ticket> getTicketById(long id) {
        return ticketRepository.findById(id);
    }

    @Override
    public List<Ticket> getTicketsByDestination(String destination) {
        return ticketRepository.findByDestination(destination);
    }

    @Override
    public List<Ticket> getTicketsByIntermediate(String intermediate) {
        return ticketRepository.findByIntermediate(intermediate);
    }

    @Override
    public List<Ticket> getTicketsByNumber(String number) {
        return ticketRepository.findByNumber(number);
    }

    @Override
    public Ticket createTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket updateTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public void deleteTicket(long id) {
        ticketRepository.deleteById(id);
    }

    @Override
    public List<Ticket> searchTickets(String destination, LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        //LocalDateTime endOfDay = date.atTime(23, 59, 59);

        List<Ticket> tickets = ticketRepository.findByDestinationOrIntermediateAndDepartureTime(destination, destination, date.atStartOfDay());
        //tickets.addAll(ticketRepository.findByIntermediateOrDestinationAndDepartureTime(destination, destination, startOfDay, endOfDay));

        return tickets;
    }
}
