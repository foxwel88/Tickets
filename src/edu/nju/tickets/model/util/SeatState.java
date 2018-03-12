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

    public SeatState(int seatNum) {
        list = new ArrayList<>();
        for (int i = 0; i < seatNum + 1; ++i) {
            list.add("false");
        }
    }

    public String getSeatSate(int seatId) {
        return list.get(seatId);
    }
}
