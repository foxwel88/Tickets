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

    private int seatNum;

    private int sellNum;

    public SeatState(int seatNum, String priceString) {
        this.seatNum = seatNum;
        this.sellNum = 0;
        list = new ArrayList<>();
        priceList = new ArrayList<>();

        for (int i = 0; i < seatNum + 1; ++i) {
            list.add("false");
        }
        String[] priceStringList = priceString.split(",");
        for (String s: priceStringList) {
            priceList.add(Double.valueOf(s));
        }
    }

    public String getSeatSate(int seatId) {
        return list.get(seatId);
    }



    public void setSeatSate(int seatId, String state) {
        if (!state.equals(list.get(seatId))) {
            if (state.equals("true")) {
                sellNum++;
            } else {
                sellNum--;
            }
        }
        list.set(seatId, state);
    }


    public double getPrice(int districtId) {
        return priceList.get(districtId);
    }

    public List<Double> getPriceList() {
        return priceList;
    }

    public void setPriceList(List<Double> priceList) {
        this.priceList = priceList;
    }

    public int getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(int seatNum) {
        this.seatNum = seatNum;
    }

    public int getSellNum() {
        return sellNum;
    }
}
