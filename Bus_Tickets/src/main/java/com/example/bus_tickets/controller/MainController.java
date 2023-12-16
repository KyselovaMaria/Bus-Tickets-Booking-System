package com.example.bus_tickets.controller;

import com.example.bus_tickets.model.Ticket;
import com.example.bus_tickets.service.PurchaseService;
import com.example.bus_tickets.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/about")
    public String showAboutPage() {
        return "about";
    }

    @GetMapping("/return")
    public String showReturnPage() {
        return "return";
    }

    @GetMapping("/contact")
    public String showContactPage() {
        return "contact";
    }

    @GetMapping("/news")
    public String showNewsPage() {
        return "news";
    }

    @GetMapping("/booking/{id}")
    public String showBookingPage(@PathVariable long id, Model model) {
        Optional<Ticket> optionalTicket = ticketService.getTicketById(id);

        if (optionalTicket.isPresent()) {
            Ticket ticket = optionalTicket.get();

            // Додавання обраного квитка до моделі
            model.addAttribute("ticket", ticket);

            return "booking"; // Назва  HTML-файлу для сторінки бронювання
        } else {
            // Логіка для випадку, коли квиток з вказаним номером не знайдено
            return "error"; // Назва  HTML-файлу для сторінки помилки , ЯКА НАРАЗІ ВІДСУТНЯ
        }
    }


}

