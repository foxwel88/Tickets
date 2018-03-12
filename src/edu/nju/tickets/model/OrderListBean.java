package edu.nju.tickets.model;

import java.io.Serializable;
import java.util.List;

public class OrderListBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<OldOrder> orderList;

	public List<OldOrder> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<OldOrder> orderList) {
		this.orderList = orderList;
	}
}
