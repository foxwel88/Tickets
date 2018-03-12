package edu.nju.tickets.model;

import edu.nju.tickets.model.util.SeatInfo;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by foxwel on 2018/3/9.
 */

@Entity
@Table(name = "tickets_pre_place")
public class PrePlace implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int placeId;

    private String passWord;

    private String name;

    private String address;

    private String describe;

    private SeatInfo seatInfo;

    private String state;

    public PrePlace(Place place) {
        this.placeId = place.getId();
        this.passWord = place.getPassWord();
        this.name = place.getName();
        this.address = place.getAddress();
        this.describe = place.getDescribe();
        this.seatInfo = place.getSeatInfo();
        this.state = place.getState();

    }

    public PrePlace() {
    }

    public PrePlace(int placeId, String passWord, String name, String address, String describe, SeatInfo seatInfo, String state) {
        this.placeId = placeId;
        this.passWord = passWord;
        this.name = name;
        this.address = address;
        this.describe = describe;
        this.seatInfo = seatInfo;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlaceId() {
        return placeId;
    }

    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describ) {
        this.describe = describ;
    }

    public SeatInfo getSeatInfo() {
        return seatInfo;
    }

    public void setSeatInfo(SeatInfo seatInfo) {
        this.seatInfo = seatInfo;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
