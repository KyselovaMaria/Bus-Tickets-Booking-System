package com.example.bus_tickets.model;

import jakarta.persistence. *;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;
    private String destination;
    private String intermediate;
    private int availableSeats;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private double basePrice;
    private double priceToIntermediate;
    private double priceToEnd;


    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    public String getFormattedDepartureTime() {
        return departureTime.format(formatter);
    }

    public String getFormattedArrivalTime() {
        return arrivalTime.format(formatter);
    }
    public Ticket(String number, String destination, String intermediate, int availableSeats,
                  LocalDateTime departureTime, LocalDateTime arrivalTime, double basePrice,
                  double priceToIntermediate, double priceToEnd) {
        this.number = number;
        this.destination = destination;
        this.intermediate = intermediate;
        this.availableSeats = availableSeats;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.basePrice = basePrice;
        this.priceToIntermediate = priceToIntermediate;
        this.priceToEnd = priceToEnd;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
