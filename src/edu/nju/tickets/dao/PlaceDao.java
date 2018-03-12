package edu.nju.tickets.dao;

import edu.nju.tickets.model.Place;
import edu.nju.tickets.model.PrePlace;
import edu.nju.tickets.model.util.ResultMessage;
import edu.nju.tickets.model.Show;

import java.util.List;

/**
 * Created by foxwel on 2018/3/8.
 */
public interface PlaceDao {
    /**
     *
     * @param place
     * @return the id of the place
     */
    int add(Place place);

    int addPrePlace(PrePlace prePlace);

    ResultMessage update(Place place);

    Place getById(int placeId);

    PrePlace getPrePlaceById(int prePlaceId);

    List<Place> getUnCheckedPlace();

    List<Place> getModifiedPlace();

}
