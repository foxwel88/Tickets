package edu.nju.tickets.dao.impl;

import edu.nju.tickets.dao.DaoHelper;
import edu.nju.tickets.dao.OrderDao;
import edu.nju.tickets.dao.TicketDao;
import edu.nju.tickets.model.OldOrder;
import edu.nju.tickets.model.Order;
import edu.nju.tickets.model.Ticket;
import edu.nju.tickets.model.util.ResultMessage;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TicketDaoImpl implements TicketDao {

	@Autowired
	private DaoHelper daoHelper;

	@Override
	public ResultMessage add(Ticket ticket) {
		daoHelper.save(ticket);
		return ResultMessage.SUCCESS;
	}

	@Override
	public Ticket getById(int ticketId) {
		Session session = daoHelper.getSession();
		session.beginTransaction();
		Ticket ticket = session.find(Ticket.class, ticketId);
		session.close();
		return ticket;
	}

}
