package edu.nju.tickets.service.impl;

import edu.nju.tickets.dao.PayDao;
import edu.nju.tickets.model.PayAccount;
import edu.nju.tickets.model.util.ResultMessage;
import edu.nju.tickets.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by foxwel on 2018/3/7.
 */

@Service
public class PayServiceImpl implements PayService{

    @Autowired
    private PayDao payDao;

    @Override
    public ResultMessage pay(String accountName, String payPassWord, double money) {

        PayAccount payAccount = payDao.getByAccountName(accountName);

        if (payAccount == null) {
            return ResultMessage.NO_PAY_ACCOUNT;
        }

        if (!payAccount.getPassWord().equals(payPassWord)) {
            return ResultMessage.WRONG_PAY_PASSWORD;
        }

        if (payAccount.getMoney() < money) {
            return ResultMessage.NO_ENOUGH_MONEY;
        }

        double oldMoney = payAccount.getMoney();
        double newMoney = oldMoney - money;

        payAccount.setMoney(newMoney);

        ResultMessage resultMessage = payDao.update(payAccount);

        return resultMessage;
    }

    @Override
    public ResultMessage returnMoney(String accountName, double money) {

        PayAccount payAccount = payDao.getByAccountName(accountName);

        if (payAccount == null) {
            return ResultMessage.NO_PAY_ACCOUNT;
        }

        double oldMoney = payAccount.getMoney();
        double newMoney = oldMoney + money;

        payAccount.setMoney(newMoney);

        ResultMessage resultMessage = payDao.update(payAccount);

        return resultMessage;
    }
}
