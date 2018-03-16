package edu.nju.tickets.dao;

import edu.nju.tickets.model.PrePlace;
import edu.nju.tickets.model.util.ResultMessage;

import java.util.List;

/**
 * Created by foxwel on 2018/3/8.
 */
public interface PrePlaceDao {

    int add(PrePlace prePlace);

    PrePlace getById(int prePlaceId);

    PrePlace getByPlaceId(int PlaceId);

    ResultMessage update(PrePlace prePlace);

    List<PrePlace> getModifiedPrePlace();

    ResultMessage delete(PrePlace prePlace);

}
