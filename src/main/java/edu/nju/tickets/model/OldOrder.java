package edu.nju.tickets.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by foxwel on 2017/12/20.
 */

@Entity
@Table(name = "userorder")
public class OldOrder implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private int id;
    private String name;
    private int num;
    private double price;
    private double totalPrice;
    private String time;
    private int isOK;
    private String userName;

    public void print() {
        System.out.println("[" + id + " " + name + " " + num + " " + price + " " + totalPrice + " " + time + " " + isOK + " " + userName + "]");
    }

    public OldOrder() {
    }

    public OldOrder(int id, String name, int num, double price, double totalPrice, String time, int isOK, String userName) {
        this.id = id;
        this.name = name;
        this.num = num;
        this.price = price;
        this.totalPrice = totalPrice;
        this.time = time;
        this.isOK = isOK;
        this.userName = userName;
    }

    @Id
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

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getIsOK() {
        return isOK;
    }

    public void setIsOK(int isOK) {
        this.isOK = isOK;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}