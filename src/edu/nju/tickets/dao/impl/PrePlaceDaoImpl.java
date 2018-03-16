package edu.nju.tickets.dao.impl;

import com.sun.org.apache.regexp.internal.RE;
import edu.nju.tickets.dao.DaoHelper;
import edu.nju.tickets.dao.PlaceDao;
import edu.nju.tickets.dao.PrePlaceDao;
import edu.nju.tickets.model.Place;
import edu.nju.tickets.model.PrePlace;
import edu.nju.tickets.model.util.ResultMessage;
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
public class PrePlaceDaoImpl implements PrePlaceDao {

    @Autowired
    DaoHelper daoHelper;

    @Override
    public int add(PrePlace prePlace) {
        daoHelper.save(prePlace);
        return prePlace.getId();
    }

    @Override
    public ResultMessage update(PrePlace prePlace) {
        daoHelper.update(prePlace);
        return ResultMessage.SUCCESS;
    }

    @Override
    public PrePlace getById(int prePlaceId) {
        Session session = daoHelper.getSession();
        Transaction tx = session.beginTransaction();
        PrePlace prePlace = session.get(PrePlace.class, prePlaceId);
        return prePlace;
    }

    @Override
    public PrePlace getByPlaceId(int PlaceId) {
        Session session = daoHelper.getSession();
        session.beginTransaction();

        String hql = "from PrePlace p where p.placeId =:PlaceId";

        Query query = session.createQuery(hql);
        query.setParameter("PlaceId", PlaceId);

        List<PrePlace> res = query.list();
        session.close();
        if (res.size() == 0) return null;else return res.get(0);
    }

    @Override
    public List<PrePlace> getModifiedPrePlace() {
        return null;
    }

    @Override
    public ResultMessage delete(PrePlace prePlace) {
        daoHelper.delete(prePlace);
        return ResultMessage.SUCCESS;
    }
}
