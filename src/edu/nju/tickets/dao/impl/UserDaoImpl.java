package edu.nju.tickets.dao.impl;

import edu.nju.tickets.dao.DaoHelper;
import edu.nju.tickets.dao.UserDao;
import edu.nju.tickets.model.util.ResultMessage;
import edu.nju.tickets.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private DaoHelper daoHelper;

    public UserDaoImpl(DaoHelper daoHelper) {
        this.daoHelper = daoHelper;
    }

    @Override
    public User getByUserName(String userName) {
        Session session = daoHelper.getSession();
        session.beginTransaction();
        User user = session.find(User.class, userName);
        session.close();
        return user;
    }

    @Override
    public ResultMessage add(User user) {
        daoHelper.save(user);
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage update(User user) {
        daoHelper.update(user);
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage delete(User user) {
        daoHelper.delete(user);
        return ResultMessage.SUCCESS;
    }
}
