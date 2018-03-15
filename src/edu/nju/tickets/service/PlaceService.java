package edu.nju.tickets.service;

import edu.nju.tickets.model.Place;
import edu.nju.tickets.model.util.ResultMessage;
import edu.nju.tickets.model.util.SeatInfo;
import edu.nju.tickets.model.Show;
import edu.nju.tickets.model.util.SeatState;

import java.util.Date;
import java.util.List;

/**
 * Created by foxwel on 2018/3/9.
 */
public interface PlaceService {

    int signUp(String passWord, String name, String address, String describ, SeatInfo seatInfo);

    int modify(Place place);

    ResultMessage addShow(String name, String describ, int placeId, Date time, List<Double> districtPriceList);

    Place getPlace(int placeId);

    Show getShow(int showId);


    List<Place> getUnCheckedPlace();

    List<Place> getModifiedPlace();

    ResultMessage checkPlaceSignUpRequest(int placeId);

    ResultMessage checkPlaceModifyRequestByPrePlaceId(int prePlaceId);
}
