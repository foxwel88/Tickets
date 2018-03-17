package edu.nju.tickets.controller;

import edu.nju.tickets.model.User;
import edu.nju.tickets.model.util.ResultMessage;
import edu.nju.tickets.model.util.SeatInfo;
import edu.nju.tickets.service.PlaceService;
import edu.nju.tickets.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by foxwel on 2018/3/14.
 */
@Controller
public class IndexController {
    private static final long serialVersionUID = 1L;

    @Autowired
    UserService userService;

    @Autowired
    PlaceService placeService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getIndex(HttpServletRequest request) {
        return "user_info";
    }

    @ResponseBody
    @RequestMapping(value = "/loginCheck", method = RequestMethod.POST)
    protected String loginCheck(@RequestParam(value = "userName",required=false) String userName,
                              @RequestParam(value = "passWord", required = false) String passWord,
                              HttpServletRequest request, HttpServletResponse response) {
        ResultMessage resultMessage = userService.login(userName, passWord);
        if (resultMessage == ResultMessage.SUCCESS) {
            request.getSession().setAttribute("manager", "root");
            if (userName.equals("root")) {
                return "root";
            }
            request.getSession().setAttribute("userName", userName);
        }
        return resultMessage.toString();
    }

    @RequestMapping(value = "/signUp", method = RequestMethod.GET)
    protected String signUp() {
        return "sign_up";
    }

    @RequestMapping(value = "/placeSignUp", method = RequestMethod.GET)
    protected String placeSignUp() {
        return "place_signUp";
    }

    @ResponseBody
    @RequestMapping(value = "/signUpPost", method = RequestMethod.POST)
    protected String signUpPost(@RequestParam(value = "userName",required=false) String userName,
                                @RequestParam(value = "passWord", required = false) String passWord,
                            @RequestParam(value = "emailAddress", required = false) String emailAddress,
                            @RequestParam(value = "phone", required = false) String phone,
                            @RequestParam(value = "checkNumber", required = false) String checkNumber,
                                HttpServletRequest request, HttpServletResponse response) {
        ResultMessage resultMessage = userService.signUp(userName, emailAddress, checkNumber, phone, passWord);
        if (resultMessage == ResultMessage.SUCCESS) {
            request.getSession().setAttribute("userName", userName);
        }
        return resultMessage.toString();
    }

    @ResponseBody
    @RequestMapping(value = "/placeSignUpPost", method = RequestMethod.POST)
    protected String placeSignUpPost(@RequestParam(value = "passWord",required=false) String passWord,
                                @RequestParam(value = "placeName", required = false) String placeName,
                                @RequestParam(value = "placeAddress", required = false) String placeAddress,
                                @RequestParam(value = "placeDescribe", required = false) String placeDescribe,
                                @RequestParam(value = "nameString", required = false) String nameString,
                                     @RequestParam(value = "infoString", required = false) String infoString,
                                HttpServletRequest request, HttpServletResponse response) {

        SeatInfo seatInfo = new SeatInfo(nameString, infoString);
        int placeId = placeService.signUp(passWord, placeName, placeAddress, placeDescribe, seatInfo);

        if (placeId != -1) {
            request.getSession().setAttribute("placeId", placeId);
        } else {
            return "FAIL";
        }

        String placeStr = String.valueOf(placeId);
        while (placeStr.length() < 7) placeStr = "0" + placeStr;
        return placeStr;
    }

    @ResponseBody
    @RequestMapping(value = "/placeLogInCheck", method = RequestMethod.POST)
    protected String placeLoginCheck(@RequestParam(value = "userName",required=false) String userName,
                                @RequestParam(value = "passWord", required = false) String passWord,
                                HttpServletRequest request) {
        ResultMessage resultMessage = placeService.logIn(userName, passWord);
        if (resultMessage == ResultMessage.SUCCESS) {
            request.getSession().setAttribute("placeId", Integer.valueOf(userName));
        }
        return resultMessage.toString();
    }

    @ResponseBody
    @RequestMapping(value = "/sendEmail", method = RequestMethod.POST)
    protected String sendEmail(@RequestParam(value = "emailAddress",required=false) String emailAddress) {
        ResultMessage resultMessage = userService.sendEmail(emailAddress);
        return resultMessage.toString();
    }


    @RequestMapping(value = "/logOutManger", method = RequestMethod.GET)
    public String logOutManger(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if ((session == null) || (session.getAttribute("manager") == null)) {
            return getIndex(request);
        }
        session.removeAttribute("manager");
        return getIndex(request);
    }

    @RequestMapping(value = "/logOutUser", method = RequestMethod.GET)
    public String logOutUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if ((session == null) || (session.getAttribute("userName") == null)) {
            return getIndex(request);
        }
        session.removeAttribute("userName");
        return getIndex(request);
    }

    @RequestMapping(value = "/logOutPlace", method = RequestMethod.GET)
    public String logOutPlace(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if ((session == null) || (session.getAttribute("placeId") == null)) {
            return getIndex(request);
        }
        session.removeAttribute("placeId");
        return getIndex(request);
    }
}
