package edu.nju.tickets.controller;


import edu.nju.tickets.model.User;
import edu.nju.tickets.model.util.ResultMessage;
import edu.nju.tickets.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Controller
public class UserController {
    @Autowired
    UserService userService;

	private static final long serialVersionUID = 1L;

	
	


    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public String getUserInfo(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            String userName = (String)session.getAttribute("userName");
            if (userName != null) {
                User user = userService.getUser(userName);
                request.setAttribute("user", user);
                return "user_info";
            } else {
                return "user_null";
            }
        } else {
            return "user_null";
        }
    }

    @RequestMapping(value = "/userChangePassWord", method = RequestMethod.GET)
    public String getUserChangePassWord(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            String userName = (String)session.getAttribute("userName");
            if (userName != null) {
                return "user_changePassWord";
            } else {
                return "user_null";
            }
        } else {
            return "user_null";
        }
    }

    @RequestMapping(value = "/userChangeEmail", method = RequestMethod.GET)
    public String getUserChangeEmail(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            String userName = (String)session.getAttribute("userName");
            if (userName != null) {
                return "user_changeEmail";
            } else {
                return "user_null";
            }
        } else {
            return "user_null";
        }
    }

    @RequestMapping(value = "/userChangeInfo", method = RequestMethod.GET)
    public String getUserChangeInfo(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            String userName = (String)session.getAttribute("userName");
            if (userName != null) {
                User user = userService.getUser(userName);
                request.setAttribute("user", user);
                return "user_changeInfo";
            } else {
                return "user_null";
            }
        } else {
            return "user_null";
        }
    }


    @ResponseBody
    @RequestMapping(value = "/modifyPassWord", method = RequestMethod.POST)
    protected String modifyPassWord(@RequestParam(value = "userName",required=false) String userName,
                              @RequestParam(value = "oldPassWord", required = false) String oldPassWord,
                                @RequestParam(value = "newPassWord", required = false) String newPassWord) {
        ResultMessage resultMessage = userService.modifyPassWord(userName, oldPassWord, newPassWord);
        return resultMessage.toString();
    }
    @ResponseBody
    @RequestMapping(value = "/modifyEmail", method = RequestMethod.POST)
    protected String modifyPassWord(@RequestParam(value = "userName",required=false) String userName,
                                    @RequestParam(value = "passWord", required = false) String passWord,
                                    @RequestParam(value = "emailAddress", required = false) String emailAddress,
                                    @RequestParam(value = "checkNumber", required = false) String checkNumber) {
        ResultMessage resultMessage = userService.modifyEmail(userName, passWord, emailAddress, checkNumber);
        return resultMessage.toString();
    }


}
