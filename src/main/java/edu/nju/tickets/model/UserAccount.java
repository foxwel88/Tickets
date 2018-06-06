package edu.nju.tickets.model;

/**
 * Created by foxwel on 2018/3/18.
 */
public class UserAccount {

    private String userName;
    private User user;
    private double price;
    private int ticketNum;

    public UserAccount(String userName, double price, int ticketNum) {
        this.userName = userName;
        this.price = price;
        this.ticketNum = ticketNum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getTicketNum() {
        return ticketNum;
    }

    public void setTicketNum(int ticketNum) {
        this.ticketNum = ticketNum;
    }
}
