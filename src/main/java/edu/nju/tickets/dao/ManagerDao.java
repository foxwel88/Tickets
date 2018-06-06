package edu.nju.tickets.dao;

import edu.nju.tickets.model.Order;
import edu.nju.tickets.model.PlaceAccount;
import edu.nju.tickets.model.TicketsAccount;
import edu.nju.tickets.model.UserAccount;

import java.util.List;

/**
 * Created by foxwel on 2018/3/18.
 */
public interface ManagerDao {
    List<PlaceAccount> getPlaceCalc();

    List<UserAccount> getUserCalc();

    TicketsAccount getTicketsCalc();

    PlaceAccount getPlaceCalcById(int placeId);
}
