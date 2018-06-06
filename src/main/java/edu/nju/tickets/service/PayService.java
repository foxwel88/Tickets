package edu.nju.tickets.service;

import edu.nju.tickets.model.util.ResultMessage;

/**
 * Created by foxwel on 2018/3/7.
 */
public interface PayService {

    public ResultMessage pay(String accountName, String payPassWord, double money);

    public ResultMessage returnMoney(String accountName, double money);

}
