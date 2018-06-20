package edu.nju.tickets.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ManagerDataDao {

    @Autowired
    private DaoHelper daoHelper;

    public List<Object[]> getProvinceIncome() {
            Session session = daoHelper.getSession();
            session.beginTransaction();

            String sql = "select p.province, sum(p.income) from tickets_place p group by p.province";

            Query query = session.createSQLQuery(sql);

            List<Object[]> res = query.list();
            session.close();
            return res;
    }

    public List<Object[]> getMonthUserNum() {
        Session session = daoHelper.getSession();
        session.beginTransaction();

        String sql = "select date_format(o.time, '%Y-%m') as order_month, count(o.username), count(if(o.olduser=1,TRUE,NULL)) " +
                "from tickets_order o " +
                "group by order_month order by order_month";

        Query query = session.createSQLQuery(sql);

        List<Object[]> res = query.list();
        session.close();
        return res;
    }

    public List<Integer> getUserNum() {
        Session session = daoHelper.getSession();
        session.beginTransaction();

        String sql = "select count(o.username), count(if(o.olduser=1,TRUE,NULL)) " +
                "from tickets_order o left join tickets_show s on o.showid = s.id ";

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


    public List<Object[]> getHighPlaceIncome() {
        Session session = daoHelper.getSession();
        session.beginTransaction();

        String sql = "select p.name, p.income, p.preincome from tickets_place p order by p.income DESC limit 5;";

        Query query = session.createSQLQuery(sql);

        List<Object[]> res = query.list();
        session.close();
        return res;
    }

    public List<Object[]> getLowPlaceIncome() {
        Session session = daoHelper.getSession();
        session.beginTransaction();

        String sql = "select p.name, p.income, p.preincome from tickets_place p order by p.income limit 5;";

        Query query = session.createSQLQuery(sql);

        List<Object[]> res = query.list();
        session.close();
        return res;
    }

    public List<Object[]> getMonthIncome() {
        Session session = daoHelper.getSession();
        session.beginTransaction();

        String sql = "select date_format(o.time, '%Y-%m') as order_month, SUM(o.price), SUM(o.payprice), SUM(o.ticketNum)*10 " +
                "from tickets_order o " +
                "group by order_month order by order_month ";

        Query query = session.createSQLQuery(sql);

        List<Object[]> res = query.list();
        session.close();
        return res;
    }

    public List<Object[]> getMonthIntegral() {
        Session session = daoHelper.getSession();
        session.beginTransaction();

        String sql = "select date_format(o.time, '%Y-%m') as order_month, count(if(o.integral>0,TRUE,NULL))/count(*) " +
                "from tickets_order o left join tickets_show s on o.showid = s.id " +
                "group by order_month order by order_month";

        Query query = session.createSQLQuery(sql);

        List<Object[]> res = query.list();
        session.close();
        return res;
    }
}
