package edu.nju.tickets.controller;


import edu.nju.tickets.model.Place;
import edu.nju.tickets.model.User;
import edu.nju.tickets.model.util.ResultMessage;
import edu.nju.tickets.service.PlaceService;
import edu.nju.tickets.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class PlaceController {
    @Autowired
    PlaceService placeService;


    @RequestMapping(value = "/placeInfo", method = RequestMethod.GET)
    public String getPlaceInfo(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if ((session == null) || (session.getAttribute("placeId") == null)) {
            return "user_null";
        }
        int placeId = (int)session.getAttribute("placeId");
        Place place = placeService.getPlace(placeId);
        request.setAttribute("place", place);
        return "place_info";
    }

    @RequestMapping(value = "/placeChangeInfo", method = RequestMethod.GET)
    public String placeChangeInfo(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if ((session == null) || (session.getAttribute("placeId") == null)) {
            return "user_null";
        }
        int placeId = (int)session.getAttribute("placeId");
        Place place = placeService.getPlace(placeId);
        request.setAttribute("place", place);
        return "place_changeInfo";
    }

    @RequestMapping(value = "/placeChangeSeatInfo", method = RequestMethod.GET)
    public String placeChangeSeatInfo(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if ((session == null) || (session.getAttribute("placeId") == null)) {
            return "user_null";
        }
        int placeId = (int)session.getAttribute("placeId");
        Place place = placeService.getPlace(placeId);
        request.setAttribute("place", place);
        return "place_changeSeatInfo";
    }

    @ResponseBody
    @RequestMapping(value = "/changePlaceSeatInfo", method = RequestMethod.POST)
    protected int changePlaceSeatInfo(@RequestParam(value = "placeId",required=false) int placeId,
                                        @RequestParam(value = "nameString", required = false) String nameString,
                                         @RequestParam(value = "infoString", required = false) String infoString){
        int prePlaceId = placeService.modifySeatInfo(placeId, nameString, infoString);
        return prePlaceId;
    }

}
