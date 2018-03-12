package edu.nju.tickets.model;

import edu.nju.tickets.model.util.SeatState;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by foxwel on 2018/3/8.
 */

@Entity
@Table(name = "tickets_show")
public class Show implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String describ;

    private int placeId;

    private Date time;

    private SeatState seatState;


    public Show() {
    }

    public Show(String name, String discrib, int placeId, Date time, SeatState seatState) {
        this.name = name;
        this.describ = discrib;
        this.placeId = placeId;
        this.time = time;
        this.seatState = seatState;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescrib() {
        return describ;
    }

    public void setDescrib(String discrib) {
        this.describ = discrib;
    }

    public int getPlaceId() {
        return placeId;
    }

    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public SeatState getSeatState() {
        return seatState;
    }

    public void setSeatState(SeatState seatState) {
        this.seatState = seatState;
    }

}
