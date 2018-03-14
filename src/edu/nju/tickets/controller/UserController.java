package edu.nju.tickets.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;


@Controller
public class UserController {
	private static final long serialVersionUID = 1L;

	
	
	
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getRegister() {
        return "user_info";
    }

    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public String getUserInfo() {
        return "user_info";
    }

    @RequestMapping(value = "/userChangePassWord", method = RequestMethod.GET)
    public String getUserChangePassWord() {
        return "user_changePassWord";
    }

    @RequestMapping(value = "/userChangeEmail", method = RequestMethod.GET)
    public String getUserChangeEmail() {
        return "user_changeEmail";
    }

    @RequestMapping(value = "/userChangeInfo", method = RequestMethod.GET)
    public String getUserChangeInfo() {
        return "user_changeInfo";
    }


    @RequestMapping(value = "/Main", method = RequestMethod.POST)
    protected String doRegist(@RequestParam(value = "userName",required=false) String userName,
    		@RequestParam(value = "passWord", required = false) String passWord,
    		HttpServletRequest request) {

		return "login";
	}
}
