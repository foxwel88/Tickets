package edu.nju.tickets.controller;

import edu.nju.tickets.model.Place;
import edu.nju.tickets.model.Show;
import edu.nju.tickets.model.util.ResultMessage;
import edu.nju.tickets.model.util.SeatInfo;
import edu.nju.tickets.service.OrderService;
import edu.nju.tickets.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by foxwel on 2018/3/15.
 */
@Controller
public class ShowController {
    @Autowired
    PlaceService placeService;

    @Autowired
    OrderService orderService;


    @RequestMapping(value = "/showSingle", method = RequestMethod.GET)
    public String getShowSingle(@RequestParam(value = "showId",required=false) String showId,
                                HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        if ((session == null) || (session.getAttribute("userName") == null)) {
            return "user_null";
        }

        if (showId == null) {
            return getShowSquare(request);
        }
        Show show = placeService.getShow(Integer.valueOf(showId));
        Place place = placeService.getPlace(show.getPlaceId());
        request.setAttribute("show", show);
        request.setAttribute("place", place);
        return "show_single";
    }

    @RequestMapping(value = "/showSquare", method = RequestMethod.GET)
    public String getShowSquare(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        if ((session == null) || (session.getAttribute("userName") == null)) {
            return "user_null";
        }

        List<Show> showList = placeService.getShowList();

        request.setAttribute("showList", showList);
        return "show_square";
    }

    @RequestMapping(value = "/showSelectSeat", method = RequestMethod.GET)
    public String showSelectSeat(@RequestParam(value = "showId",required=false) int showId,
                                 @RequestParam(value = "districtId",required=false) int districtId,
                                 HttpServletRequest request) {

        Show show = placeService.getShow(showId);
        Place place = placeService.getPlace(show.getPlaceId());

        request.setAttribute("show", show);
        request.setAttribute("place", place);
        request.setAttribute("districtId", districtId);
        return "show_selectSeat";
    }

    @ResponseBody
    @RequestMapping(value = "/createNotOrder", method = RequestMethod.POST)
    public String createNotOrder(@RequestParam(value = "showId",required=false) int showId,
                              @RequestParam(value = "districtId",required=false) int districtId,
                              @RequestParam(value = "ticketNum",required=false) int ticektNum,
                              HttpServletRequest request) {
        String userName = (String)request.getSession().getAttribute("userName");
        ResultMessage resultMessage = orderService.createNotSelectOrder(userName, showId, districtId, ticektNum);
        return resultMessage.toString();
    }

    @ResponseBody
    @RequestMapping(value = "/createSelectOrder", method = RequestMethod.POST)
    public String createSelectOrder(@RequestParam(value = "userName",required=false) String userName,
                                @RequestParam(value = "showId",required=false) int showId,
                                 @RequestParam(value = "price",required=false) double price,
                                @RequestParam(value = "districtId",required=false) int districtId,
                                 @RequestParam(value = "seatStr",required=false) String seatStr,
                                 HttpServletRequest request) {
        Show show = placeService.getShow(showId);
        Place place = placeService.getPlace(show.getPlaceId());
        SeatInfo seatInfo = place.getSeatInfo();
        List<Integer> seatList = new ArrayList<>();
        String[] seatStrList = seatStr.split("@");

        for (String currentSeatStr: seatStrList) {
            String[] temp = currentSeatStr.split("_");
            int rowId = Integer.valueOf(temp[0]);
            int sitId = Integer.valueOf(temp[1]);
            seatList.add(seatInfo.getSeatId(districtId, rowId, sitId));
        }

        ResultMessage resultMessage = orderService.createSelectOrder(userName, showId, price, seatList);
        return resultMessage.toString();
    }
}
