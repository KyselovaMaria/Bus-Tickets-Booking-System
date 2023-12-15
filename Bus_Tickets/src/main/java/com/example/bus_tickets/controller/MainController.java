package com.example.bus_tickets.controller;

import com.example.bus_tickets.model.Ticket;
import com.example.bus_tickets.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {

    private final TicketService ticketService;

    @Autowired
    public MainController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/")
    public String home(Model model) {
        // Отримання розкладу всіх наявних рейсів
        List<Ticket> allTickets = ticketService.getAllTickets();

        // Додавання розкладу до моделі
        model.addAttribute("tickets", allTickets);

        return "home"; // Повернення на основну сторінку
    }
}
