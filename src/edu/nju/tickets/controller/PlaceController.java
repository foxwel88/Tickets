package edu.nju.tickets.controller;


import edu.nju.tickets.model.Place;
import edu.nju.tickets.model.PlaceAccount;
import edu.nju.tickets.model.Show;
import edu.nju.tickets.service.ManagerService;
import edu.nju.tickets.service.PlaceService;
import edu.nju.tickets.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;


@Controller
public class PlaceController {
    @Autowired
    PlaceService placeService;

    @Autowired
    ManagerService managerService;

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

    @RequestMapping(value = "/placeShow", method = RequestMethod.GET)
    public String placeShowInfo(@RequestParam(value = "showId",required=false) String showId,
                                HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if ((session == null) || (session.getAttribute("placeId") == null)) {
            return "user_null";
        }

        int placeId = (int)request.getSession().getAttribute("placeId");
        Place place = placeService.getPlace(placeId);
        request.setAttribute("place", place);

        List<Show> showList = placeService.getShowListByPlaceId(placeId);
        request.setAttribute("showList", showList);

        Show show = null;
        if (showId == null) {
            if (showList.size() > 0) {
                show = showList.get(0);
            }
        } else {
            show = placeService.getShow(Integer.valueOf(showId));
        }
        request.setAttribute("show", show);
        return "place_show";
    }

    @RequestMapping(value = "/placeCreateShow", method = RequestMethod.GET)
    public String placeCreateShow(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if ((session == null) || (session.getAttribute("placeId") == null)) {
            return "user_null";
        }

        int placeId = (int)request.getSession().getAttribute("placeId");
        Place place = placeService.getPlace(placeId);
        request.setAttribute("place", place);

        List<Show> showList = placeService.getShowListByPlaceId(placeId);
        request.setAttribute("showList", showList);

        return "place_createShow";
    }


    @RequestMapping(value = "/placeCalc", method = RequestMethod.GET)
    public String placeCalc(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if ((session == null) || (session.getAttribute("placeId") == null)) {
            return "user_null";
        }

        PlaceAccount placeAccount = managerService.getPlaceCalcById((int)session.getAttribute("placeId"));
        request.setAttribute("placeAccount", placeAccount);

        return "place_calc";
    }

    @ResponseBody
    @RequestMapping(value = "/changePlaceInfo", method = RequestMethod.POST)
    protected int changePlaceInfo(@RequestParam(value = "placeId",required=false) int placeId,
                                      @RequestParam(value = "placeName", required = false) String placeName,
                                      @RequestParam(value = "placeAddress", required = false) String placeAddress,
                                      @RequestParam(value = "placeDescribe", required = false) String placeDescribe){
        int prePlaceId = placeService.modifyInfo(placeId, placeName, placeAddress,placeDescribe);
        return prePlaceId;
    }

    @ResponseBody
    @RequestMapping(value = "/changePlaceSeatInfo", method = RequestMethod.POST)
    protected int changePlaceSeatInfo(@RequestParam(value = "placeId",required=false) int placeId,
                                        @RequestParam(value = "nameString", required = false) String nameString,
                                         @RequestParam(value = "infoString", required = false) String infoString){
        int prePlaceId = placeService.modifySeatInfo(placeId, nameString, infoString);
        return prePlaceId;
    }

    @ResponseBody
    @RequestMapping(value = "/createShow", method = RequestMethod.POST)
    protected int createShow(@RequestParam(value = "placeId",required=false) int placeId,
                                @RequestParam(value = "showName", required = false) String showName,
                                @RequestParam(value = "showDescribe", required = false) String showDescribe,
                                @RequestParam(value = "showTime", required = false) String showTime,
                                @RequestParam(value = "priceString", required = false) String priceString){
        Date date = TimeUtil.parseString(showTime);
        int showId = placeService.addShow(showName, showDescribe, placeId, date, priceString);
        return showId;
    }

}
