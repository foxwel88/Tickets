package edu.nju.tickets.service.impl;

import edu.nju.tickets.OrderVO;
import edu.nju.tickets.dao.OrderDao;
import edu.nju.tickets.dao.TicketDao;
import edu.nju.tickets.model.*;
import edu.nju.tickets.model.util.ResultMessage;
import edu.nju.tickets.model.util.SeatInfo;
import edu.nju.tickets.model.util.SeatState;
import edu.nju.tickets.service.OrderService;
import edu.nju.tickets.service.PayService;
import edu.nju.tickets.service.PlaceService;
import edu.nju.tickets.service.UserService;
import edu.nju.tickets.util.LevelUtil;
import edu.nju.tickets.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

	@Autowired
	private PlaceService placeService;

	@Autowired
	private OrderService orderService;


	@Override
	public ResultMessage createSelectOrder(String userName, int showId, double price, List<Integer> seatIdList) {
		int ticketNum = seatIdList.size();
		double totalPrice = price * ticketNum;

		Order order = new Order(userName, showId, "select", ticketNum, new Date(), new Date(), totalPrice, "unPaied", 0);
		int orderId = orderDao.add(order);

		Show show = placeService.getShow(showId);
		Place place = placeService.getPlace(show.getPlaceId());

		for (int seatId: seatIdList) {
			show.getSeatState().setSeatSate(seatId, "true");
			Ticket ticket = new Ticket(showId, orderId, seatId, place.getSeatInfo().getSeatName(seatId), price);
			ticketDao.add(ticket);
		}

		placeService.modify(show);
		userService.addMoney(userName, totalPrice);

		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage createNotSelectOrder(String userName, int showId, int districtId, int ticektNum) {
		Show show = placeService.getShow(showId);
		SeatState seatState = show.getSeatState();
		double price = seatState.getPriceList().get(districtId);

		double totalPrice = price * ticektNum;
		Date orderDate = new Date();


		Date checkDate = TimeUtil.calcCheckDate(show.getTime());


		Order order = new Order(userName, showId, "notselect", ticektNum, orderDate, checkDate, totalPrice, "unPaied", districtId);
		orderDao.add(order);

		userService.addMoney(userName, totalPrice);

		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage payOrder(String userName, int orderId, String payAccountName, String payAccountPassWord) {
		User user = userService.getUser(userName);
		Order order = getOrder(orderId);

		double price = order.getPrice();
		double count = LevelUtil.getCount(user.getLevel());
		price = price * count;


		ResultMessage payResultMessage = payService.pay(payAccountName, payAccountPassWord, price);

		if (payResultMessage != ResultMessage.SUCCESS) {
			return payResultMessage;
		}

		order.setPayPrice(price);
		order.setState("paied");
		orderDao.update(order);
		return ResultMessage.SUCCESS;
	}

	@Override
	public Order getOrder(int orderId) {
		return orderDao.getById(orderId);
	}

	@Override
	public List<Order> getUnPaiedOrderList(String userName) {
		return orderDao.getOrderListByUserNameAndState(userName, "unPaied");
	}

	@Override
	public List<Order> getPaiedOrderList(String userName) {
		return orderDao.getOrderListByUserNameAndState(userName, "paied");
	}

	@Override
	public List<Order> getOldOrderList(String userName) {
		return orderDao.getOrderListByUserNameAndState(userName, "old");
	}

	@Override
	public List<OrderVO> getUnPaiedOrderVOList(String userName) {
		List<Order> orderList = getUnPaiedOrderList(userName);
		List<OrderVO> orderVOList = new ArrayList<>();
		for (Order order: orderList) {
			orderVOList.add(new OrderVO(order, placeService, orderService));
		}
		return orderVOList;
	}

	@Override
	public List<OrderVO> getPaiedOrderVOList(String userName) {
		List<Order> orderList = getPaiedOrderList(userName);
		List<OrderVO> orderVOList = new ArrayList<>();
		for (Order order: orderList) {
			orderVOList.add(new OrderVO(order, placeService, orderService));
		}
		return orderVOList;
	}

	@Override
	public List<OrderVO> getOldOrderVOList(String userName) {
		List<Order> orderList = getOldOrderList(userName);
		List<OrderVO> orderVOList = new ArrayList<>();
		for (Order order: orderList) {
			orderVOList.add(new OrderVO(order, placeService, orderService));
		}
		return orderVOList;
	}

	@Override
	public List<Ticket> getTicketList(int orderId) {
		return ticketDao.getByOrderId(orderId);
	}



	@Override
	public List<OldOrder> getOrderList(String userName) {
		return orderDao.getOrderList(userName);
	}

	@Override
	public ResultMessage cancelOrder(int orderId, String payAccountId) {
		Order order = orderDao.getById(orderId);
		payService.returnMoney(payAccountId, order.getPrice());
		order.setState("old");
		return orderDao.update(order);
	}

	@Override
	public ResultMessage cancelOrder(int orderId) {
		Order order = orderDao.getById(orderId);
		order.setState("old");
		return orderDao.update(order);
	}
}
