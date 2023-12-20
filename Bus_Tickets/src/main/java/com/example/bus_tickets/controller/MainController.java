package com.example.bus_tickets.controller;

import com.example.bus_tickets.model.*;
import com.example.bus_tickets.service.*;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class MainController {

    private final TicketService ticketService;
    private final UserService userService;
    private final PurchaseService purchaseService;

    @Autowired
    public MainController(TicketService ticketService,UserService userService, PurchaseService purchaseService) {
        this.ticketService = ticketService;
        this.userService = userService;
        this.purchaseService = purchaseService;
    }


    @GetMapping("/")
    public String home(Model model) {
        List<Ticket> allTickets = ticketService.getAllTickets();
        model.addAttribute("tickets", allTickets);

        return "home";
    }

    @GetMapping("/about")
    public String showAboutPage() {
        return "about";
    }

    @GetMapping("/return")
    public String showReturnPage() {
        return "return";
    }

    @PostMapping("/return")
    public ResponseEntity<String> returnTicket(@RequestParam String name,
                                               @RequestParam String email,
                                               @RequestParam  Long purchaseId) {
        try {

            User user = userService.getUserByEmail(email);
            PurchaseInfo purchaseInfo = purchaseService.getPurchaseInfo(purchaseId);

            if (user != null && purchaseInfo != null && purchaseInfo.getUser().equals(user)) {
                Ticket ticket = purchaseInfo.getPurchasedTicket();
                ticket.setAvailableSeats(ticket.getAvailableSeats()+ purchaseInfo.getQuantity());
                ticketService.updateTicket(ticket);

                return ResponseEntity.ok("Квиток успішно повернуто.");
            } else {
                return ResponseEntity.badRequest().body("Помилка при поверненні квитка.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Помилка обробки запиту.");
        }
    }


    @GetMapping("/contact")
    public String showContactPage() {
        return "contact";
    }

    @GetMapping("/news")
    public String showNewsPage() {
        return "news";
    }

    @GetMapping("/booking/{ticketid}")
    public String showBookingPage(@PathVariable("ticketid") long ticketid,  Model model) {
        Optional<Ticket> optionalTicket = ticketService.getTicketById(ticketid);

        if (optionalTicket.isPresent()) {
            Ticket ticket = optionalTicket.get();
            model.addAttribute("ticket", ticket);
            return "booking"; // Назва HTML-файлу для сторінки бронювання
        } else {
            return "home"; // Назва HTML-файлу для сторінки помилки, ЯКА НАРАЗІ ВІДСУТНЯ
        }
    }

    @PostMapping("/booking/{ticketid}")
    public ResponseEntity<String> purchaseTicket(@PathVariable("ticketid") long ticketid, @RequestParam String name,
                                                 @RequestParam String email, @RequestParam int quantity,
                                                 @RequestParam String ticketType) {
        try {
            //System.out.println("Name: " + name);
            //System.out.println("Email: " + email);
            User user = new User(name, email);
            Optional<Ticket> optionalTicket = ticketService.getTicketById(ticketid);

            List<Ticket> allTickets = ticketService.getAllTickets();
            //System.out.println(allTickets);

            if (optionalTicket.isPresent()) {
                Ticket ticket = optionalTicket.get();
                double totalPrice = calculateTotalPrice(ticket, quantity, "intermediate".equals(ticketType));

                if (user != null) {
                    if (quantity <= 0 || quantity > ticket.getAvailableSeats()) {
                        return ResponseEntity.badRequest().body("Недійсна кількість. " +
                                "Будь ласка, виберіть дійсну кількість квитків. Доступно: " + ticket.getAvailableSeats());
                    }
                    else{
                        PurchaseInfo purchaseInfo = new PurchaseInfo(user, ticket, quantity, totalPrice);
                        //System.out.println(purchaseInfo);

                        user.setPurchases(new ArrayList<>());

                        user.getPurchases().add(purchaseInfo);
                        userService.updateUser(user);
                        ticket.setAvailableSeats(ticket.getAvailableSeats()-quantity);
                        ticketService.updateTicket(ticket);

                        //System.out.println("Num of available seats " + ticket.getAvailableSeats());

                        return ResponseEntity.ok("Оплата успішно здійснена. Дякуємо за покупку " + quantity +
                                " квитк(ів)." +"   Cума: "+ purchaseInfo.getTotalPrice()+
                                "   ID покупки: "+ purchaseInfo.getPurchaseId() );
                    }

                } else {
                    return ResponseEntity.badRequest().body("Помилка при покупці квитка.");
                }
            } else {
                return ResponseEntity.badRequest().body("Квиток з номером " + ticketid + " не знайдено.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Помилка обробки запиту.");
        }
    }

    private double calculateTotalPrice(Ticket ticket, int quantity, boolean toIntermediate) {
        double price = toIntermediate ? ticket.getPriceToIntermediate() : ticket.getPriceToEnd();
        return price * quantity;
    }


    @PostMapping("/search")
    public String searchTickets(@RequestParam String destination, @RequestParam String date, Model model) {
        List<Ticket> searchResults = ticketService.searchTickets(destination, LocalDate.parse(date));
        System.out.println("Found:" + searchResults);

        // Assuming you have a method in your service to get the first ticket ID
        Long firstTicketId = ticketService.getFirstTicketId(searchResults);

        // Redirect to the booking page with the first ticket ID
        return "redirect:/booking/{firstTicketId}";
    }

}
