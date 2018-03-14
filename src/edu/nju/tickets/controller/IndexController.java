package edu.nju.tickets.controller;

import edu.nju.tickets.model.util.ResultMessage;
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

/**
 * Created by foxwel on 2018/3/14.
 */
@Controller
public class IndexController {
    private static final long serialVersionUID = 1L;

    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping(value = "/loginCheck", method = RequestMethod.POST)
    protected String doRegist(@RequestParam(value = "userName",required=false) String userName,
                              @RequestParam(value = "passWord", required = false) String passWord,
                              HttpServletRequest request, HttpServletResponse response) {
        ResultMessage resultMessage = userService.login(userName, passWord);
        if (resultMessage == ResultMessage.SUCCESS) {
            request.getSession().setAttribute("userName", userName);
        }
        return resultMessage.toString();
    }
}
