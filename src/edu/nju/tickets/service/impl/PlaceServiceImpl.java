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
    public ResultMessage modify(Show show) {
        return showDao.update(show);
    }

    @Override
    public int modifySeatInfo(int placeId, String nameString, String infoString) {
        Place place = getPlace(placeId);
        SeatInfo seatInfo = new SeatInfo(nameString, infoString);
        place.setSeatInfo(seatInfo);

        PrePlace prePlace = prePlaceDao.getByPlaceId(place.getId());
        if (prePlace != null) {
            place.setName(prePlace.getName());
            place.setDescribe(prePlace.getDescribe());
            place.setAddress(prePlace.getAddress());
        }
        return modify(place);
    }

    @Override
    public int modifyInfo(int placeId, String placeName, String placeAddress, String placeDescribe) {
        Place place = getPlace(placeId);
        place.setName(placeName);
        place.setDescribe(placeDescribe);
        place.setAddress(placeAddress);

        PrePlace prePlace = prePlaceDao.getByPlaceId(place.getId());
        if (prePlace != null) {
            place.setSeatInfo(prePlace.getSeatInfo());
        }
        return modify(place);
    }

    @Override
    public int addShow(String name, String describe, int placeId, Date time, String districtPriceString) {
        /**
         * create a blank seatState
         */
        Place place = placeDao.getById(placeId);
        SeatInfo seatInfo = place.getSeatInfo();
        SeatState seatState = new SeatState(seatInfo.getTotalSeatNum(), districtPriceString);
        Show show = new Show(name, describe, placeId, time, seatState);
        showDao.add(show);
        return show.getId();
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
    public List<Show> getShowListByPlaceId(int placeId) {
        return showDao.getByPlaceId(placeId);
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
    public ResultMessage unCheckPlaceSignUpRequest(int placeId) {
        Place place = getPlace(placeId);
        return placeDao.delete(place);
    }

    @Override
    public ResultMessage checkPlaceModifyRequestByPrePlaceId(int prePlaceId) {
        PrePlace prePlace = prePlaceDao.getById(prePlaceId);
        Place place = new Place(prePlace);
        placeDao.update(place);
        prePlaceDao.delete(prePlace);
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage unCheckPlaceModifyRequestByPrePlaceId(int prePlaceId) {
        PrePlace prePlace = prePlaceDao.getById(prePlaceId);
        prePlaceDao.delete(prePlace);
        return ResultMessage.SUCCESS;
    }

    @Override
    public List<Show> getShowList() {
        return showDao.getList();
    }
}
