package edu.nju.tickets.controller;

import edu.nju.tickets.model.Place;
import edu.nju.tickets.model.Show;
import edu.nju.tickets.model.util.ResultMessage;
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
    public String getRegister(@RequestParam(value = "showId",required=false) String showId,
                                HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        if ((session == null) || (session.getAttribute("userName") == null)) {
            return "user_null";
        }

        if (showId == null) {
            return "user_null";
        }
        Show show = placeService.getShow(Integer.valueOf(showId));
        Place place = placeService.getPlace(show.getPlaceId());
        request.setAttribute("show", show);
        request.setAttribute("place", place);
        return "show_single";
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
}
