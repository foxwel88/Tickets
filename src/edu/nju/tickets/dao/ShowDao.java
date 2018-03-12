package edu.nju.tickets.dao;

import edu.nju.tickets.model.Place;
import edu.nju.tickets.model.Show;
import edu.nju.tickets.model.util.ResultMessage;

/**
 * Created by foxwel on 2018/3/8.
 */
public interface ShowDao {

    ResultMessage add(Show show);

    Show getById(int id);

}
