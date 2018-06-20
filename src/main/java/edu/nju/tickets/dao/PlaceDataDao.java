package edu.nju.tickets.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PlaceDataDao {

    @Autowired
    private DaoHelper daoHelper;

    public List<Object[]> getPlaceMonthUserNum(int placeID) {
            Session session = daoHelper.getSession();
            session.beginTransaction();

            String sql = "select date_format(o.time, '%Y-%m') as order_month, count(o.username), count(if(o.olduser=1,TRUE,NULL)) " +
                    "from tickets_order o left join tickets_show s on o.showid = s.id " +
                    "where s.placeid = " + placeID + " " +
                    "group by order_month order by order_month";

            Query query = session.createSQLQuery(sql);

            List<Object[]> res = query.list();
            session.close();
            return res;
    }

    public List<Integer> getPlaceUserNum(int placeID) {
        Session session = daoHelper.getSession();
        session.beginTransaction();

        String sql = "select count(o.username), count(if(o.olduser=1,TRUE,NULL)) " +
                "from tickets_order o left join tickets_show s on o.showid = s.id " +
                "where s.placeid = " + placeID;

        Query query = session.createSQLQuery(sql);

        List<Object[]> res = query.list();

        List<Integer> ans = new ArrayList<>();
        int allNum = Integer.valueOf(res.get(0)[0].toString());
        int goodNum = Integer.valueOf(res.get(0)[1].toString());
        int badNum = allNum - goodNum;

        ans.add(badNum);
        ans.add(goodNum);

        session.close();
        return ans;
    }

    public List<Object[]> getPlaceShowSeatPercent(int placeID) {
        Session session = daoHelper.getSession();
        session.beginTransaction();

        String sql = "select s.name, s.seatpercent " +
                "from tickets_show s " +
                "where s.placeid = " + placeID;

        Query query = session.createSQLQuery(sql);

        List<Object[]> res = query.list();
        session.close();
        return res;
    }

    public List<Object[]> getPlaceIncome(int placeID) {
        Session session = daoHelper.getSession();
        session.beginTransaction();

        String sql = "select date_format(o.time, '%Y-%m') as order_month, SUM(o.price), SUM(o.payprice) " +
                "from tickets_order o left join tickets_show s on o.showid = s.id " +
                "where s.placeid = " + placeID + " " +
                "group by order_month order by order_month";

        Query query = session.createSQLQuery(sql);

        List<Object[]> res = query.list();
        session.close();
        return res;
    }

    public List<Object[]> getPlaceMonthIntegral(int placeID) {
        Session session = daoHelper.getSession();
        session.beginTransaction();

        String sql = "select date_format(o.time, '%Y-%m') as order_month, count(if(o.integral>0,TRUE,NULL))/count(*) " +
                "from tickets_order o left join tickets_show s on o.showid = s.id " +
                "where s.placeid = " + placeID + " " +
                "group by order_month order by order_month";

        Query query = session.createSQLQuery(sql);

        List<Object[]> res = query.list();
        session.close();
        return res;
    }
}
