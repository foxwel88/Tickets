package edu.nju.tickets.dao;

import edu.nju.tickets.model.Coupon;
import edu.nju.tickets.model.util.ResultMessage;

import java.util.List;

/**
 * Created by foxwel on 2018/3/18.
 */
public interface CouponDao {
    List<Coupon> getByUserName(String userName);

    ResultMessage delete(int id);

    ResultMessage add(Coupon coupon);

    Coupon getById(int id);
}
