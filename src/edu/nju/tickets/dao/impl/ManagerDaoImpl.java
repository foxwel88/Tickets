package edu.nju.tickets.dao.impl;

import edu.nju.tickets.dao.DaoHelper;
import edu.nju.tickets.dao.ManagerDao;
import edu.nju.tickets.dao.PlaceDao;
import edu.nju.tickets.dao.UserDao;
import edu.nju.tickets.model.*;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by foxwel on 2018/3/18.
 */
@Repository
public class ManagerDaoImpl implements ManagerDao {

    @Autowired
    DaoHelper daoHelper;

    @Autowired
    PlaceDao placeDao;

    @Autowired
    UserDao userDao;

    @Override
    public List<PlaceAccount> getPlaceCalc() {
        Session session = daoHelper.getSession();
        session.beginTransaction();

        String hql = "select p.id, sum(o.price), count(distinct s.id), sum(o.ticketNum) from Order o, Show  s, Place p where o.showId = s.id and s.placeId = p.id and o.state = 'paied' group by p.id";

        Query query = session.createQuery(hql);

        List<Object[]> res = query.list();
        List<PlaceAccount> placeAccountList = new ArrayList();

        for (Object[] ob: res) {
            int id = (int)ob[0];
            double price = (double)ob[1];
            int showNum = (int)(long)ob[2];
            int ticketNum = (int)(long)ob[3];
            System.out.println(id + " " + price + " " + showNum + " " + ticketNum);
            PlaceAccount placeAccount = new PlaceAccount(id, price, showNum, ticketNum);
            Place place = placeDao.getById(id);
            placeAccount.setPlace(place);
            placeAccountList.add(placeAccount);
        }
        session.close();
        return placeAccountList;
    }

    @Override
    public PlaceAccount getPlaceCalcById(int placeId) {
        Session session = daoHelper.getSession();
        session.beginTransaction();

        String hql = "select sum(o.price), count(distinct s.id), sum(o.ticketNum) from Order o, Show  s where o.showId = s.id and s.placeId =:placeId and o.state = 'paied' ";

        Query query = session.createQuery(hql);
        query.setParameter("placeId", placeId);
        List<Object[]> res = query.list();
        List<PlaceAccount> placeAccountList = new ArrayList();


            double price = (double)res.get(0)[0];
            int showNum = (int)(long)res.get(0)[1];
            int ticketNum = (int)(long)res.get(0)[2];
            int id = placeId;
            PlaceAccount placeAccount = new PlaceAccount(id, price, showNum, ticketNum);
            Place place = placeDao.getById(id);
            placeAccount.setPlace(place);

        session.close();
        return placeAccount;
    }

    @Override
    public List<UserAccount> getUserCalc() {
        Session session = daoHelper.getSession();
        session.beginTransaction();

        String hql = "select u.userName, sum(o.price), sum(o.ticketNum) from Order o, User  u where o.userName = u.userName and o.state = 'paied' group by u.userName";

        Query query = session.createQuery(hql);

        List<Object[]> res = query.list();
        List<UserAccount> userAccountList = new ArrayList();

        for (Object[] ob: res) {
            String userName = (String)ob[0];
            double price = (double)ob[1];
            int ticketNum = (int)(long)ob[2];
            System.out.println(userName + " " + price + " " + ticketNum);
            UserAccount userAccount = new UserAccount(userName, price, ticketNum);
            User user = userDao.getByUserName(userName);
            userAccount.setUser(user);
            userAccountList.add(userAccount);
        }
        session.close();
        return userAccountList;
    }

    @Override
    public TicketsAccount getTicketsCalc() {
        Session session = daoHelper.getSession();
        session.beginTransaction();

        String hql = "select sum(o.price), count(*), sum(o.ticketNum) from Order o where o.state = 'unPaied'";
        Query query = session.createQuery(hql);
        List<Object[]> res = query.list();

        double unPaiedPrice = 0;
        int unPaiedOrderNum = 0;
        int unPaiedTicketNum = 0;
        double paiedPrice = 0;
        int paiedOrderNum = 0;
        int paiedTicketNum = 0;

        System.out.println(res);
        System.out.println(res.size());
        if ((res != null) && (res.size() > 0) && (res.get(0) != null) && (res.get(0)[0] != null)) {
             unPaiedPrice = (double) res.get(0)[0];
             unPaiedOrderNum = (int) (long) res.get(0)[1];
             unPaiedTicketNum = (int) (long) res.get(0)[2];
        }

        hql = "select sum(o.price), count(*), count(o.ticketNum) from Order o where o.state = 'paied'";
        query = session.createQuery(hql);
        res = query.list();

        if ((res != null) && (res.size() > 0) && (res.get(0) != null) && (res.get(0)[0] != null)) {
            paiedPrice = (double) res.get(0)[0];
            paiedOrderNum = (int) (long) res.get(0)[1];
            paiedTicketNum = (int) (long) res.get(0)[2];
        }


        System.out.println(unPaiedPrice + " " + unPaiedOrderNum + " " + unPaiedTicketNum);
        System.out.println(paiedPrice + " " + paiedOrderNum + " " + paiedTicketNum);

        TicketsAccount ticketsAccount = new TicketsAccount(unPaiedPrice, unPaiedOrderNum, unPaiedTicketNum, paiedPrice, paiedOrderNum, paiedTicketNum);

        session.close();
        return ticketsAccount;
    }
}
