package edu.nju.tickets.service.impl;

import edu.nju.tickets.dao.ManagerDao;
import edu.nju.tickets.model.Place;
import edu.nju.tickets.model.PlaceAccount;
import edu.nju.tickets.model.TicketsAccount;
import edu.nju.tickets.model.UserAccount;
import edu.nju.tickets.model.util.ResultMessage;
import edu.nju.tickets.service.ManagerService;
import edu.nju.tickets.service.OrderService;
import edu.nju.tickets.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by foxwel on 2018/3/10.
 */
@Service
public class ManagerServiceImpl implements ManagerService{

    @Autowired
    ManagerDao managerDao;

    @Override
    public List<PlaceAccount> getPlaceCalc() {
        return managerDao.getPlaceCalc();
    }

    @Override
    public List<UserAccount> getUserCalc() {
        return managerDao.getUserCalc();
    }

    @Override
    public TicketsAccount getTicketsCalc() {
        return managerDao.getTicketsCalc();
    }

    @Override
    public PlaceAccount getPlaceCalcById(int placeId) {
        return managerDao.getPlaceCalcById(placeId);
    }

}
