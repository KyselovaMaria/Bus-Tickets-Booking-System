package com.example.bus_tickets;

import com.example.bus_tickets.model.Ticket;
import com.example.bus_tickets.repository.TicketRepository;
import com.example.bus_tickets.service.impl.TicketServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TicketServiceTest {

    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private TicketServiceImpl ticketService;

    private Ticket testTicket;

    @Before
    public void setUp() {
        testTicket = new Ticket();
        testTicket.setId(1L);
        testTicket.setDestination("TestDestination");
        testTicket.setIntermediate("TestIntermediate");
        testTicket.setNumber("T123");
        testTicket.setDepartureTime(LocalDate.now().atStartOfDay());

        when(ticketRepository.save(any(Ticket.class))).thenReturn(testTicket);
        when(ticketRepository.findById(1L)).thenReturn(Optional.of(testTicket));
        when(ticketRepository.findAll()).thenReturn(new ArrayList<>());
    }

    @Test
    public void testCreateTicket() {
        Ticket createdTicket = ticketService.createTicket(testTicket);
        assertEquals(testTicket.getId(), createdTicket.getId());
    }

    @Test
    public void testGetTicketById() {
        Optional<Ticket> retrievedTicket = ticketService.getTicketById(1L);
        assertEquals(testTicket, retrievedTicket.orElse(null));
    }

    @Test
    public void testUpdateTicket() {
        Ticket updatedTicket = ticketService.updateTicket(testTicket);
        assertEquals(testTicket, updatedTicket);
    }

    @Test
    public void testDeleteTicket() {
        ticketService.deleteTicket(1L);
        verify(ticketRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testGetAllTickets() {
        List<Ticket> tickets = ticketService.getAllTickets();
        assertEquals(0, tickets.size());
    }

}