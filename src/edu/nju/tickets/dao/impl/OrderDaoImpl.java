package edu.nju.tickets.dao.impl;

import edu.nju.tickets.dao.DaoHelper;
import edu.nju.tickets.dao.OrderDao;
import edu.nju.tickets.model.OldOrder;
import edu.nju.tickets.model.Order;
import edu.nju.tickets.model.Ticket;
import edu.nju.tickets.model.User;
import edu.nju.tickets.model.util.ResultMessage;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {

	@Autowired
	private DaoHelper daoHelper;

	@Override
	public List<OldOrder> getOrderList(String userName) {
		Session session = daoHelper.getSession();
		Transaction tx = session.beginTransaction();
		String hql = "from OldOrder o where o.userName =:username";
		Query query = session.createQuery(hql);
		query.setParameter("username", userName);
		List<OldOrder> res = query.list();
		if ((session != null) && session.isOpen()) session.close();
		return res;
	}

	@Override
	public int add(Order order) {
		daoHelper.save(order);
		return order.getId();
	}


	@Override
	public Order getById(int orderId) {
		Session session = daoHelper.getSession();
		session.beginTransaction();
		Order order = session.find(Order.class, orderId);
		session.close();
		return order;
	}

	@Override
	public ResultMessage update(Order order) {
		daoHelper.update(order);
		return ResultMessage.SUCCESS;
	}

	@Override
	public List<Order> getOrderListByUserNameAndState(String userName, String state) {
		System.out.println(userName + "   " + state);
		Session session = daoHelper.getSession();
		session.beginTransaction();

		String hql = "from Order o where o.userName =:username and o.state =:state";

		Query query = session.createQuery(hql);
		query.setParameter("username", userName);
		query.setParameter("state", state);

		List<Order> res = query.list();
		System.out.println(res.size());
		session.close();
		return res;
	}

	@Override
	public ResultMessage delete(Order order) {
		daoHelper.delete(order);
		return ResultMessage.SUCCESS;
	}


}
