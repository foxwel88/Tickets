package edu.nju.tickets.dao.impl;

import edu.nju.tickets.dao.CouponDao;
import edu.nju.tickets.dao.DaoHelper;
import edu.nju.tickets.model.Coupon;
import edu.nju.tickets.model.Order;
import edu.nju.tickets.model.util.ResultMessage;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by foxwel on 2018/3/18.
 */

@Repository
public class CouponDaoImpl implements CouponDao {

    @Autowired
    DaoHelper daoHelper;

    @Override
    public List<Coupon> getByUserName(String userName) {
        Session session = daoHelper.getSession();
        session.beginTransaction();

        String hql = "from Coupon c where c.userName =:username";

        Query query = session.createQuery(hql);
        query.setParameter("username", userName);
        List<Coupon> res = query.list();
        session.close();
        return res;
    }

    @Override
    public ResultMessage delete(int id) {
        daoHelper.delete(getById(id));
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage add(Coupon coupon) {
        daoHelper.save(coupon);
        return ResultMessage.SUCCESS;
    }

    @Override
    public Coupon getById(int id) {
        Session session = daoHelper.getSession();
        session.beginTransaction();
        Coupon coupon = session.find(Coupon.class, id);
        session.close();
        return coupon;
    }
}
