package edu.nju.tickets.model.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by foxwel on 2018/3/8.
 */
public class SeatState implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<String> list;

    private List<Double> priceList;

    public SeatState(int seatNum, List<Double> districtPriceList) {
        list = new ArrayList<>();
        priceList = new ArrayList<>();

        for (int i = 0; i < seatNum + 1; ++i) {
            list.add("false");
        }
        this.priceList.addAll(districtPriceList);
    }

    public String getSeatSate(int seatId) {
        return list.get(seatId);
    }

    public void setSeatSate(int seatId, String state) {
        list.set(seatId, state);
    }

    public List<Double> getPriceList() {
        return priceList;
    }

    public void setPriceList(List<Double> priceList) {
        this.priceList = priceList;
    }
}
