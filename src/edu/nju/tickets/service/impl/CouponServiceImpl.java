package edu.nju.tickets.service.impl;

import edu.nju.tickets.dao.CouponDao;
import edu.nju.tickets.model.Coupon;
import edu.nju.tickets.model.util.ResultMessage;
import edu.nju.tickets.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by foxwel on 2018/3/18.
 */
@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    CouponDao couponDao;

    @Override
    public List<Coupon> getByUserName(String userName) {
        return couponDao.getByUserName(userName);
    }

    @Override
    public ResultMessage delete(int id) {
        return couponDao.delete(id);
    }

    @Override
    public ResultMessage add(Coupon coupon) {
        return couponDao.add(coupon);
    }

    @Override
    public Coupon getById(int id) {
        return couponDao.getById(id);
    }
}
