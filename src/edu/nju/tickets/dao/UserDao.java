package edu.nju.tickets.dao;

import edu.nju.tickets.model.util.ResultMessage;
import edu.nju.tickets.model.User;


public interface UserDao {

	User getByUserName(String userName);

	ResultMessage add(User user);

	ResultMessage update(User user);

    ResultMessage delete(User user);
}
