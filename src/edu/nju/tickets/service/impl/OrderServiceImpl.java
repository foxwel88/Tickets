package edu.nju.tickets.service.impl;

import edu.nju.tickets.dao.OrderDao;
import edu.nju.tickets.dao.TicketDao;
import edu.nju.tickets.model.OldOrder;
import edu.nju.tickets.model.Order;
import edu.nju.tickets.model.Ticket;
import edu.nju.tickets.model.util.ResultMessage;
import edu.nju.tickets.service.OrderService;
import edu.nju.tickets.service.PayService;
import edu.nju.tickets.service.UserService;
import edu.nju.tickets.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {


	@Autowired
	private OrderDao orderDao;

	@Autowired
	private TicketDao ticketDao;

	@Autowired
	private PayService payService;

	@Autowired
	private UserService userService;


	@Override
	public ResultMessage createSelectOrder(String userName, int showId, double price, List<Integer> seatIdList) {
		int ticketNum = seatIdList.size();
		double totalPrice = price * ticketNum;

		Order order = new Order(userName, showId, "select", ticketNum, new Date(), new Date(), totalPrice, "false");
		int orderId = orderDao.add(order);

		for (int seatId: seatIdList) {
			Ticket ticket = new Ticket(showId, orderId, seatId, "seatname", price);
			ticketDao.add(ticket);
		}

		userService.addMoney(userName, totalPrice);

		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage createNotSelectOrder(String userName, int showId, double price, int ticektNum) {
		double totalPrice = price * ticektNum;
		Date orderDate = new Date();
		Date checkDate = TimeUtil.calcCheckDate(orderDate);

		Order order = new Order(userName, showId, "notselect", ticektNum, orderDate, checkDate, totalPrice, "false");
		int orderId = orderDao.add(order);

		userService.addMoney(userName, totalPrice);

		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage payOrder(int orderId, String payAccountName, String payAccountPassWord) {
		Order order = getOrder(orderId);
		ResultMessage payResultMessage = payService.pay(payAccountName, payAccountPassWord, order.getPrice());
		if (payResultMessage != ResultMessage.SUCCESS) {
			return payResultMessage;
		}
		order.setState("true");
		orderDao.update(order);
		return ResultMessage.SUCCESS;
	}

	@Override
	public Order getOrder(int orderId) {
		return orderDao.getById(orderId);
	}


	@Override
	public List<OldOrder> getOrderList(String userName) {
		return orderDao.getOrderList(userName);
	}

	@Override
	public ResultMessage cancelOrder(int orderId) {
		return null;
	}
}
