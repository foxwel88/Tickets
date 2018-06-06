package edu.nju.tickets.model;

import edu.nju.tickets.util.TimeUtil;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by foxwel on 2018/3/18.
 */
@Entity
@Table(name = "tickets_coupon")
public class Coupon {
    @Id
    private int id;

    private String userName;

    private int highPrice;

    private int lowPrice;

    private Date stopDate;

    public Coupon() {
    }

    public Coupon(String userName, int highPrice, int lowPrice, Date stopDate) {
        this.userName = userName;
        this.highPrice = highPrice;
        this.lowPrice = lowPrice;
        this.stopDate = stopDate;
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

    public int getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(int highPrice) {
        this.highPrice = highPrice;
    }

    public int getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(int lowPrice) {
        this.lowPrice = lowPrice;
    }

    public Date getStopDate() {
        return stopDate;
    }

    public void setStopDate(Date stopDate) {
        this.stopDate = stopDate;
    }

    public String getName() {
        return "满" + highPrice + "元减" + lowPrice + "元";
    }

    public String getDateString() {
        return TimeUtil.getDayString(stopDate);
    }
}
