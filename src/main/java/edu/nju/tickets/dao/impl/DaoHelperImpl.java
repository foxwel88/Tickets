package edu.nju.tickets.dao.impl;

import edu.nju.tickets.dao.DaoHelper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DaoHelperImpl implements DaoHelper
{
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.openSession();
	}

	@Override
	public void save(Object object) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		session.save(object);
		tx.commit();
		session.close();
	}


	@Override
	public void update(Object object) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		session.update(object);
		tx.commit();
		session.close();
	}

	@Override
	public void delete(Object object) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		session.delete(object);
		tx.commit();
		session.close();
	}

}
