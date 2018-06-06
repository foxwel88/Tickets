package edu.nju.tickets.model;

import javax.persistence.*;

/**
 * Created by foxwel on 2018/3/8.
 */

@Entity
@Table (name = "tickets_ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int showId;

    private int orderId;

    private int seatId;

    private String seatName;

    private double price;

    public Ticket() {
    }

    public Ticket(int showId, int orderId, int seatId, String seatName, double price) {
        this.showId = showId;
        this.orderId = orderId;
        this.seatId = seatId;
        this.seatName = seatName;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getShowId() {
        return showId;
    }

    public void setShowId(int showId) {
        this.showId = showId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public String getSeatName() {
        return seatName;
    }

    public void setSeatName(String seatName) {
        this.seatName = seatName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
