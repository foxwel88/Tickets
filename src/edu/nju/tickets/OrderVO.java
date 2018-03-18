package edu.nju.tickets;

import edu.nju.tickets.model.Order;
import edu.nju.tickets.model.Place;
import edu.nju.tickets.model.Show;
import edu.nju.tickets.model.Ticket;
import edu.nju.tickets.service.OrderService;
import edu.nju.tickets.service.PlaceService;
import edu.nju.tickets.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by foxwel on 2018/3/15.
 */
public class OrderVO {


    private int id;

    private String day;

    private String time;

    private int showId;

    private String showName;

    private String showDay;

    private String type;

    private int num;

    private String seat;

    private double price;

    private List<Ticket> ticketList;

    public OrderVO(Order order, PlaceService placeService, OrderService orderService) {
        this.id = order.getId();
        this.day = TimeUtil.getDayString(order.getTime());
        this.time = TimeUtil.getTimeString(order.getTime());
        this.showId = order.getShowId();
        Show show = placeService.getShow(showId);
        Place place = placeService.getPlace(show.getPlaceId());
        this.showName = show.getName();
        this.showDay = TimeUtil.getDayString(show.getTime());
        this.type = order.getType();
        this.num = order.getTicketNum();
        this.price = order.getPrice();

        if (this.type.equals("notselect")) {
            this.type = "未选座订单<br>[配票时间:" + TimeUtil.getDayString(order.getCheckTime()) + "]";
            this.seat = place.getSeatInfo().getDistrictName(order.getDistrictId());
        } else {
            this.type = "选座订单";
            seat = "";
            ticketList = orderService.getTicketList(id);
            for (Ticket ticket: ticketList) {
                seat = seat + ticket.getSeatName() + "<br>";
            }
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getShowId() {
        return showId;
    }

    public void setShowId(int showId) {
        this.showId = showId;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getShowDay() {
        return showDay;
    }

    public void setShowDay(String showDay) {
        this.showDay = showDay;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }
}
