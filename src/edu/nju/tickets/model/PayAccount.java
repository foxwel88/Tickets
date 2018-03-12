package edu.nju.tickets.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by foxwel on 2018/3/7.
 */
@Entity
@Table(name = "tickets_pay_account")
public class PayAccount {

    @Id
    private String name;

    private String passWord;

    private double money;

    public PayAccount() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public void print() {
        System.out.println("[PayAccount] { " + name + " " + " " + passWord + " " + money + "}");
    }
}
