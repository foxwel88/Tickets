package edu.nju.tickets.service;

import edu.nju.tickets.model.OldOrder;
import edu.nju.tickets.model.Order;
import edu.nju.tickets.model.util.ResultMessage;

import java.util.List;

public interface OrderService {

	public ResultMessage createSelectOrder(String userName, int showId, double price, List<Integer> seatIdList);

	public ResultMessage createNotSelectOrder(String userName, int showId, double price, int ticektNum);

	public ResultMessage payOrder(int orderId, String payAccountName, String payAccountPassWord);

	public Order getOrder(int orderId);

	public List<OldOrder> getOrderList(String userName);

	public ResultMessage cancelOrder(int orderId);

}
