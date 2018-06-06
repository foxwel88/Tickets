package edu.nju.tickets.dao;

import edu.nju.tickets.model.Ticket;
import edu.nju.tickets.model.util.ResultMessage;

import java.util.List;

public interface TicketDao {

    ResultMessage add(Ticket ticket);

    Ticket getById(int ticketId);

    List<Ticket> getByOrderId(int orderId);
}
