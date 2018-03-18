package edu.nju.tickets.service;

import edu.nju.tickets.model.Coupon;
import edu.nju.tickets.model.util.ResultMessage;

import java.util.List;

/**
 * Created by foxwel on 2018/3/18.
 */
public interface CouponService {
    List<Coupon> getByUserName(String userName);

    ResultMessage delete(int id);

    ResultMessage add(Coupon coupon);

    Coupon getById(int id);
}
