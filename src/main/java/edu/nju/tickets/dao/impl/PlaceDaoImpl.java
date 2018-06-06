package edu.nju.tickets.dao.impl;

import edu.nju.tickets.dao.DaoHelper;
import edu.nju.tickets.dao.PlaceDao;
import edu.nju.tickets.model.Place;
import edu.nju.tickets.model.PrePlace;
import edu.nju.tickets.model.Ticket;
import edu.nju.tickets.model.util.ResultMessage;
import edu.nju.tickets.model.Show;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by foxwel on 2018/3/8.
 */

@Repository
public class PlaceDaoImpl implements PlaceDao {

    @Autowired
    DaoHelper daoHelper;

    @Override
    public int add(Place place) {
        daoHelper.save(place);
        return place.getId();
    }

    @Override
    public ResultMessage update(Place place) {
        daoHelper.update(place);
        return ResultMessage.SUCCESS;
    }

    @Override
    public Place getById(int placeId) {
        Session session = daoHelper.getSession();
        Transaction tx = session.beginTransaction();
        Place place = session.get(Place.class, placeId);
        return place;
    }

    @Override
    public List<Place> getUnCheckedPlace() {
        Session session = daoHelper.getSession();
        session.beginTransaction();

        String hql = "from Place p where p.state='false'";
        Query query = session.createQuery(hql);

        List<Place> res = query.list();
        session.close();
        return res;
    }

    @Override
    public ResultMessage delete(Place place) {
        daoHelper.delete(place);
        return ResultMessage.SUCCESS;
    }

}
