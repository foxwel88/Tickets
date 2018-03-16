package edu.nju.tickets.service.impl;

import edu.nju.tickets.dao.PlaceDao;
import edu.nju.tickets.dao.PrePlaceDao;
import edu.nju.tickets.dao.ShowDao;
import edu.nju.tickets.model.Place;
import edu.nju.tickets.model.PrePlace;
import edu.nju.tickets.model.User;
import edu.nju.tickets.model.util.ResultMessage;
import edu.nju.tickets.model.util.SeatInfo;
import edu.nju.tickets.model.Show;
import edu.nju.tickets.model.util.SeatState;
import edu.nju.tickets.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by foxwel on 2018/3/9.
 */
@Service
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    private PlaceDao placeDao;

    @Autowired
    private PrePlaceDao prePlaceDao;

    @Autowired
    private ShowDao showDao;

    @Override
    public int signUp(String passWord, String name, String address, String describ, SeatInfo seatInfo) {
        Place place = new Place(passWord, name, address, describ, seatInfo, "false");
        int id = placeDao.add(place);
        return id;
    }

    @Override
    public ResultMessage logIn(String userName, String passWord) {
        if ((userName == null) || (passWord == null)) {
            return ResultMessage.NO_USER;
        }

        int id = Integer.valueOf(userName);
        Place place = placeDao.getById(id);

        if (place == null) {
            return ResultMessage.NO_USER;
        }

        if (!place.getPassWord().equals(passWord)) {
            return ResultMessage.WRONG_PASSWORD;
        } else {
            return ResultMessage.SUCCESS;
        }
    }

    @Override
    public int modify(Place place) {
        PrePlace prePlace = prePlaceDao.getByPlaceId(place.getId());
        if (prePlace == null) {
            prePlace = new PrePlace(place);
            return prePlaceDao.add(prePlace);
        } else {
            prePlace.modify(place);
            prePlaceDao.update(prePlace);
            return prePlace.getId();
        }
    }

    @Override
    public int modifySeatInfo(int placeId, String nameString, String infoString) {
        Place place = getPlace(placeId);
        SeatInfo seatInfo = new SeatInfo(nameString, infoString);
        place.setSeatInfo(seatInfo);
        return modify(place);
    }

    @Override
    public ResultMessage addShow(String name, String describ, int placeId, Date time, List<Double> districtPriceList) {
        /**
         * create a blank seatState
         */
        Place place = placeDao.getById(placeId);
        SeatInfo seatInfo = place.getSeatInfo();
        SeatState seatState = new SeatState(seatInfo.getTotalSeatNum(), districtPriceList);
        Show show = new Show(name, describ, placeId, time, seatState);
        return showDao.add(show);
    }

    @Override
    public Place getPlace(int placeId) {
        return placeDao.getById(placeId);
    }

    @Override
    public Show getShow(int showId) {
        return showDao.getById(showId);
    }


    @Override
    public List<Place> getUnCheckedPlace() {
        return placeDao.getUnCheckedPlace();
    }

    @Override
    public List<PrePlace> getModifiedPlace() {
        return prePlaceDao.getModifiedPrePlace();
    }

    @Override
    public ResultMessage checkPlaceSignUpRequest(int placeId) {
        Place place = getPlace(placeId);
        place.setState("true");
        return placeDao.update(place);
    }

    @Override
    public ResultMessage checkPlaceModifyRequestByPrePlaceId(int prePlaceId) {
        PrePlace prePlace = prePlaceDao.getById(prePlaceId);
        Place place = new Place(prePlace);
        placeDao.update(place);
        prePlaceDao.delete(prePlace);
        return ResultMessage.SUCCESS;
    }
}
