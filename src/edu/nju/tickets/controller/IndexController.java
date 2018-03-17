package edu.nju.tickets.controller;

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
    public String getRegister(HttpServletRequest request) {
        return "show_single";
    }

    @ResponseBody
    @RequestMapping(value = "/loginCheck", method = RequestMethod.POST)
    protected String loginCheck(@RequestParam(value = "userName",required=false) String userName,
                              @RequestParam(value = "passWord", required = false) String passWord,
                              HttpServletRequest request, HttpServletResponse response) {
        ResultMessage resultMessage = userService.login(userName, passWord);
        if (resultMessage == ResultMessage.SUCCESS) {
            request.getSession().setAttribute("userName", userName);
            if (userName.equals("root")) {
                return "root";
            }
        }
        return resultMessage.toString();
    }

    @ResponseBody
    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    protected String signUp(@RequestParam(value = "userName",required=false) String userName,
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
}
