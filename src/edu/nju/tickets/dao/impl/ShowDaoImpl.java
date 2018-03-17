package edu.nju.tickets.dao.impl;

import edu.nju.tickets.dao.DaoHelper;
import edu.nju.tickets.dao.ShowDao;
import edu.nju.tickets.model.Show;
import edu.nju.tickets.model.Ticket;
import edu.nju.tickets.model.util.ResultMessage;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by foxwel on 2018/3/10.
 */
@Repository
public class ShowDaoImpl implements ShowDao {

    @Autowired
    DaoHelper daoHelper;

    @Override
    public ResultMessage add(Show show) {
        daoHelper.save(show);
        return ResultMessage.SUCCESS;
    }

    @Override
    public Show getById(int id) {
        Session session = daoHelper.getSession();
        Transaction tx = session.beginTransaction();
        Show show = session.get(Show.class, id);
        tx.commit();
        session.close();
        return show;
    }

    @Override
    public List<Show> getByPlaceId(int placeId) {
        Session session = daoHelper.getSession();
        session.beginTransaction();

        String hql = "from Show s where s.placeId =:placeId";

        Query query = session.createQuery(hql);
        query.setParameter("placeId", placeId);

        List<Show> res = query.list();
        session.close();
        return res;
    }
}
