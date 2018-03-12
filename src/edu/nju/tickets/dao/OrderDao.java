package edu.nju.tickets.dao;

import edu.nju.tickets.model.OldOrder;
import edu.nju.tickets.model.Order;
import edu.nju.tickets.model.Ticket;
import edu.nju.tickets.model.util.ResultMessage;

import java.util.List;

public interface OrderDao {

    List<OldOrder> getOrderList(String userName);

    int add(Order order);

    Order getById(int orderId);

    ResultMessage update(Order order);
}
