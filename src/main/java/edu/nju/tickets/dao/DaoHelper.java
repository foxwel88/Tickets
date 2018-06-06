package edu.nju.tickets.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public interface DaoHelper 
{

	public SessionFactory getSessionFactory();

	public Session getSession();

	void save(Object object);

	void update(Object object);

	void delete(Object object);
}
