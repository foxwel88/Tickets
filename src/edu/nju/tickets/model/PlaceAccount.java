package edu.nju.tickets.model;

/**
 * Created by foxwel on 2018/3/18.
 */
public class PlaceAccount {

    private int id;
    private Place place;
    private double price;
    private int showNum;
    private int ticketNum;

    public PlaceAccount(int id, double price, int showNum, int ticketNum) {
        this.id = id;
        this.price = price;
        this.showNum = showNum;
        this.ticketNum = ticketNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public int getShowNum() {
        return showNum;
    }

    public void setShowNum(int showNum) {
        this.showNum = showNum;
    }

    public int getTicketNum() {
        return ticketNum;
    }

    public void setTicketNum(int ticketNum) {
        this.ticketNum = ticketNum;
    }
}
