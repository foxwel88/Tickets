package edu.nju.tickets.service;

import edu.nju.tickets.OrderVO;
import edu.nju.tickets.model.OldOrder;
import edu.nju.tickets.model.Order;
import edu.nju.tickets.model.Ticket;
import edu.nju.tickets.model.util.ResultMessage;

import java.util.List;

public interface OrderService {

	public ResultMessage createSelectOrder(String userName, int showId, double price, List<Integer> seatIdList);

	public ResultMessage createNotSelectOrder(String userName, int showId, int districtId, int ticektNum);

	public ResultMessage payOrder(String userName, int orderId, String payAccountName, String payAccountPassWord);

	public Order getOrder(int orderId);

	public List<Order> getUnPaiedOrderList(String userName);

	public List<Order> getPaiedOrderList(String userName);

	public List<Order> getOldOrderList(String userName);


	public List<OrderVO> getUnPaiedOrderVOList(String userName);

	public List<OrderVO> getPaiedOrderVOList(String userName);

	public List<OrderVO> getOldOrderVOList(String userName);


	public List<Ticket> getTicketList(int orderId);

	public ResultMessage cancelOrder(int orderId);



	public List<OldOrder> getOrderList(String userName);


}
