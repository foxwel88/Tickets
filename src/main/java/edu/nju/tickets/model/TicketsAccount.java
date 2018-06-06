package edu.nju.tickets.model;

/**
 * Created by foxwel on 2018/3/18.
 */
public class TicketsAccount {
    double unPaiedPrice;

    int unPaiedOrderNum;

    int unPaiedTicketNum;

    double paiedPrice;

    int paiedOrderNum;

    int paiedTicketNum;

    public TicketsAccount(double unPaiedPrice, int unPaiedOrderNum, int unPaiedTicketNum, double paiedPrice, int paiedOrderNum, int paiedTicketNum) {
        this.unPaiedPrice = unPaiedPrice;
        this.unPaiedOrderNum = unPaiedOrderNum;
        this.unPaiedTicketNum = unPaiedTicketNum;
        this.paiedPrice = paiedPrice;
        this.paiedOrderNum = paiedOrderNum;
        this.paiedTicketNum = paiedTicketNum;
    }

    public double getUnPaiedPrice() {
        return unPaiedPrice;
    }

    public void setUnPaiedPrice(double unPaiedPrice) {
        this.unPaiedPrice = unPaiedPrice;
    }

    public int getUnPaiedOrderNum() {
        return unPaiedOrderNum;
    }

    public void setUnPaiedOrderNum(int unPaiedOrderNum) {
        this.unPaiedOrderNum = unPaiedOrderNum;
    }

    public int getUnPaiedTicketNum() {
        return unPaiedTicketNum;
    }

    public void setUnPaiedTicketNum(int unPaiedTicketNum) {
        this.unPaiedTicketNum = unPaiedTicketNum;
    }

    public double getPaiedPrice() {
        return paiedPrice;
    }

    public void setPaiedPrice(double paiedPrice) {
        this.paiedPrice = paiedPrice;
    }

    public int getPaiedOrderNum() {
        return paiedOrderNum;
    }

    public void setPaiedOrderNum(int paiedOrderNum) {
        this.paiedOrderNum = paiedOrderNum;
    }

    public int getPaiedTicketNum() {
        return paiedTicketNum;
    }

    public void setPaiedTicketNum(int paiedTicketNum) {
        this.paiedTicketNum = paiedTicketNum;
    }
}
