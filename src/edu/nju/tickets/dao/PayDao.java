package edu.nju.tickets.dao;

import edu.nju.tickets.model.PayAccount;
import edu.nju.tickets.model.util.ResultMessage;

/**
 * Created by foxwel on 2018/3/7.
 */


public interface PayDao {

    public PayAccount getByAccountName(String accountName);

    public ResultMessage update(PayAccount payAccount);

    public ResultMessage addPayRecord(String accountName, double oldMoney, double costMoney, double newMoney, String time);

}
