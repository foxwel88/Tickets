package edu.nju.tickets.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface DaoHelper 
{

	public SessionFactory getSessionFactory();

	public Session getSession();

	void save(Object object);

	void update(Object object);

	void delete(Object object);
}
