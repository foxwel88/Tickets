package edu.nju.tickets.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by foxwel on 2018/1/10.
 */

@Entity
@Table(name = "tickets_user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String userName;

    private String passWord;

    private String emailAddress;

    private String phone;

    private double money;

    private int integral;

    private int level;

    private String state;

    public User() {
    }

    public User(String userName, String passWord, String emailAddress, String phone) {
        this.userName = userName;
        this.passWord = passWord;
        this.emailAddress = emailAddress;
        this.phone = phone;
        this.money = 0;
        this.integral = 0;
        this.level = 1;
        this.state = "true";
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
