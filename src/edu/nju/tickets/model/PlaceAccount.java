package edu.nju.tickets.model;

/**
 * Created by foxwel on 2018/3/18.
 */
public class PlaceAccount {
    int id;
    double price;

    public PlaceAccount(int id, double price) {
        this.id = id;
        this.price = price;
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
}
