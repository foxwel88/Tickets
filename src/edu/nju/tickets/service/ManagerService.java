package edu.nju.tickets.service;

import edu.nju.tickets.model.PlaceAccount;
import edu.nju.tickets.model.TicketsAccount;
import edu.nju.tickets.model.UserAccount;
import edu.nju.tickets.model.util.ResultMessage;

import java.util.List;

/**
 * Created by foxwel on 2018/3/9.
 */
public interface ManagerService {
    List<PlaceAccount> getPlaceCalc();

    List<UserAccount> getUserCalc();

    TicketsAccount getTicketsCalc();

    PlaceAccount getPlaceCalcById(int placeId);

}
