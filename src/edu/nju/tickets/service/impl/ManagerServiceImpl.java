package edu.nju.tickets.service.impl;

import edu.nju.tickets.model.Place;
import edu.nju.tickets.model.util.ResultMessage;
import edu.nju.tickets.service.ManagerService;
import edu.nju.tickets.service.OrderService;
import edu.nju.tickets.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by foxwel on 2018/3/10.
 */
public class ManagerServiceImpl implements ManagerService{

    @Autowired
    PlaceService placeService;

}
