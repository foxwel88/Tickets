package edu.nju.tickets.controller;


import edu.nju.tickets.model.Place;
import edu.nju.tickets.model.PrePlace;
import edu.nju.tickets.model.Show;
import edu.nju.tickets.model.util.ResultMessage;
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
public class ManagerController {
    @Autowired
    PlaceService placeService;


    @RequestMapping(value = "/managerCheck", method = RequestMethod.GET)
    public String managerCheck(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if ((session == null) || (session.getAttribute("userName") == null) || (!session.getAttribute("userName").equals("root"))) {
            return "user_null";
        }

        List<PrePlace> prePlaceList = placeService.getModifiedPlace();
        request.setAttribute("prePlaceList", prePlaceList);

        return "manager_check";
    }


    @RequestMapping(value = "/managerCheckSignUp", method = RequestMethod.GET)
    public String managerCheckSignUp(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if ((session == null) || (session.getAttribute("userName") == null) || (!session.getAttribute("userName").equals("root"))) {
            return "user_null";
        }

        List<Place> placeList = placeService.getUnCheckedPlace();
        request.setAttribute("placeList", placeList);

        return "manager_checkSignUp";
    }

    @RequestMapping(value = "/managerCalc", method = RequestMethod.GET)
    public String managerCalc(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if ((session == null) || (session.getAttribute("userName") == null) || (!session.getAttribute("userName").equals("root"))) {
            return "user_null";
        }

        return "manager_calc";
    }

    @RequestMapping(value = "/managerInfo", method = RequestMethod.GET)
    public String managerInfo(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if ((session == null) || (session.getAttribute("userName") == null) || (!session.getAttribute("userName").equals("root"))) {
            return "user_null";
        }

        return "manager_info";
    }

    @ResponseBody
    @RequestMapping(value = "/checkModify", method = RequestMethod.POST)
    protected String checkModify(@RequestParam(value = "prePlaceId",required=false) int prePlaceId) {
        ResultMessage resultMessage = placeService.checkPlaceModifyRequestByPrePlaceId(prePlaceId);
        return resultMessage.toString();
    }

    @ResponseBody
    @RequestMapping(value = "/unCheckModify", method = RequestMethod.POST)
    protected String unCheckModify(@RequestParam(value = "prePlaceId",required=false) int prePlaceId) {
        ResultMessage resultMessage = placeService.unCheckPlaceModifyRequestByPrePlaceId(prePlaceId);
        return resultMessage.toString();
    }

    @ResponseBody
    @RequestMapping(value = "/checkSignUp", method = RequestMethod.POST)
    protected String checkSignUp(@RequestParam(value = "placeId",required=false) int placeId) {
        ResultMessage resultMessage = placeService.checkPlaceSignUpRequest(placeId);
        return resultMessage.toString();
    }

    @ResponseBody
    @RequestMapping(value = "/unCheckSignUp", method = RequestMethod.POST)
    protected String unCheckSignUp(@RequestParam(value = "placeId",required=false) int placeId) {
        ResultMessage resultMessage = placeService.unCheckPlaceSignUpRequest(placeId);
        return resultMessage.toString();
    }
}
