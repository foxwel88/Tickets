package edu.nju.tickets.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by foxwel on 2018/3/7.
 */

@Entity
@Table(name = "tickets_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String userName;

    private int showId;

    private String type;

    private int ticketNum;

    private Date time;

    private Date checkTime;

    private double price;

    private String state;

    public Order() {
    }

    public Order(String userName, int showId, String type, int ticketNum, Date time, Date checkTime, double price, String state) {
        this.userName = userName;
        this.showId = showId;
        this.type = type;
        this.ticketNum = ticketNum;
        this.time = time;
        this.checkTime = checkTime;
        this.price = price;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getShowId() {
        return showId;
    }

    public void setShowId(int showId) {
        this.showId = showId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTicketNum() {
        return ticketNum;
    }

    public void setTicketNum(int ticketNum) {
        this.ticketNum = ticketNum;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
