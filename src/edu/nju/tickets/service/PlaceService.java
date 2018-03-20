package edu.nju.tickets.service;

import edu.nju.tickets.model.Place;
import edu.nju.tickets.model.PrePlace;
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

    ResultMessage logIn(String userName, String passWord);

    int modify(Place place);

    ResultMessage modify(Show show);

    int modifySeatInfo(int placeId, String nameString, String infoString);

    int modifyInfo(int placeId, String placeName, String placeAddress, String placeDescribe);

    int addShow(String name, String describ, int placeId, Date time, String districtPriceString);

    Place getPlace(int placeId);

    Show getShow(int showId);

    List<Show> getShowListByPlaceId(int placeId);


    List<Place> getUnCheckedPlace();

    List<PrePlace> getModifiedPlace();

    ResultMessage checkPlaceSignUpRequest(int placeId);

    ResultMessage unCheckPlaceSignUpRequest(int placeId);

    ResultMessage checkPlaceModifyRequestByPrePlaceId(int prePlaceId);

    ResultMessage unCheckPlaceModifyRequestByPrePlaceId(int prePlaceId);

    List<Show> getShowList();

    List<Show> getBySearch(String searchString);
}
