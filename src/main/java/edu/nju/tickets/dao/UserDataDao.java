package edu.nju.tickets.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDataDao {

    @Autowired
    private DaoHelper daoHelper;

    public List<Object[]> getPlaceIncome(String userName) {
        Session session = daoHelper.getSession();
        session.beginTransaction();

        String sql = "select date_format(o.time, '%Y-%m') as order_month, SUM(o.price), SUM(o.payprice) " +
                "from tickets_order o " +
                "where o.username = '" + userName + "' " +
                "group by order_month order by order_month";

        Query query = session.createSQLQuery(sql);

        List<Object[]> res = query.list();
        session.close();
        return res;
    }

    public List<Object[]> getPlaceMonthIntegral(String userName) {
        Session session = daoHelper.getSession();
        session.beginTransaction();

        String sql = "select date_format(o.time, '%Y-%m') as order_month, sum(o.integral) " +
                "from tickets_order o " +
                "where o.username = '" + userName + "' " +
                "group by order_month order by order_month";

        Query query = session.createSQLQuery(sql);

        List<Object[]> res = query.list();
        session.close();
        return res;
    }
}
