package edu.nju.tickets.dao.impl;

import edu.nju.tickets.dao.DaoHelper;
import edu.nju.tickets.dao.ManagerDao;
import edu.nju.tickets.model.Order;
import edu.nju.tickets.model.PlaceAccount;
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

    @Override
    public List<PlaceAccount> getPlaceCalc() {
        Session session = daoHelper.getSession();
        session.beginTransaction();

        String hql = "select p.id, sum(o.price) from Order o, Show  s, Place p where o.showId = s.id and s.placeId = p.id and o.state = 'paied' group by p.id";

        Query query = session.createQuery(hql);

        List<Object[]> res = query.list();
        List<PlaceAccount> placeAccountList = new ArrayList();

        for (Object[] ob: res) {
            int id = (int)ob[0];
            double price = (double)ob[1];
            System.out.println(id + " " + price);
            PlaceAccount placeAccount = new PlaceAccount(id, price);
            placeAccountList.add(placeAccount);
        }
        session.close();
        return placeAccountList;
    }
}
