package edu.nju.tickets.dao.impl;

import edu.nju.tickets.dao.DaoHelper;
import edu.nju.tickets.dao.PayDao;
import edu.nju.tickets.model.PayAccount;
import edu.nju.tickets.model.util.ResultMessage;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by foxwel on 2018/3/7.
 */
@Repository
public class PayDaoImpl implements PayDao {

    @Autowired
    DaoHelper daoHelper;

    @Override
    public PayAccount getByAccountName(String accountName) {
        Session session = daoHelper.getSession();
        session.beginTransaction();
        PayAccount payAccount = session.find(PayAccount.class, accountName);
        session.close();
        return payAccount;
    }


    @Override
    public ResultMessage update(PayAccount payAccount) {
        daoHelper.update(payAccount);
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage addPayRecord(String accountName, double oldMoney, double costMoney, double newMoney, String time) {
        return null;
    }
}
