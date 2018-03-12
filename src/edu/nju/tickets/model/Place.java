package edu.nju.tickets.model;

import edu.nju.tickets.model.util.SeatInfo;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by foxwel on 2018/3/9.
 */

@Entity
@Table(name = "tickets_place")
public class Place implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String passWord;

    private String name;

    private String address;

    private String describe;

    private SeatInfo seatInfo;

    private String state;

    public Place() {
    }

    public Place(PrePlace prePlace) {
        this.id = prePlace.getId();
        this.passWord = prePlace.getPassWord();
        this.name = prePlace.getName();
        this.address = prePlace.getAddress();
        this.describe = prePlace.getDescribe();
        this.seatInfo = prePlace.getSeatInfo();
        this.state = prePlace.getState();
    }

    public Place(String passWord, String name, String address, String describe, SeatInfo seatInfo, String state) {
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

    public void setDescribe(String describe) {
        this.describe = describe;
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
