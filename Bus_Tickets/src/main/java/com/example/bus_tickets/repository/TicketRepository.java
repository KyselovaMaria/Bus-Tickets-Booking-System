package com.example.bus_tickets.repository;

import com.example.bus_tickets.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByDestination(String destination);
    List<Ticket> findByIntermediate(String intermediate);
    List<Ticket> findByNumber(String number);

    //List<Ticket> findByDestinationAndDepartureTime(String destination, LocalDateTime departureTime);

    List<Ticket> findByDestinationOrIntermediateAndDepartureTime(String destination, String intermediate, LocalDateTime departureTime);
}