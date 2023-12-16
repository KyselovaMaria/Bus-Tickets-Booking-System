package com.example.bus_tickets.service.impl;

import com.example.bus_tickets.model.Ticket;
import com.example.bus_tickets.repository.TicketRepository;
import com.example.bus_tickets.service.TicketService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }


    @PostConstruct
    public void init() {
        // Створення об'єктів Ticket і додавання їх до бази даних при старті додатку
        List<Ticket> initialTickets = Arrays.asList(
                new Ticket("123", "Львів", "Ужгород", 50,
                        LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(3),
                        100.0, 20.0, 30.0),
                new Ticket("456", "Чоп","Львів" , 30,
                        LocalDateTime.now().plusHours(3), LocalDateTime.now().plusHours(6),
                        120.0, 25.0, 35.0),
                new Ticket("789", "Дніпро", "Харків", 40,
                        LocalDateTime.now().plusHours(2), LocalDateTime.now().plusHours(4),
                        90.0, 15.0, 25.0)
                // Додайте більше рейсів, якщо потрібно
        );

        ticketRepository.saveAll(initialTickets);
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
