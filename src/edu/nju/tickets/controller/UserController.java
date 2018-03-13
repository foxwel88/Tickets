package edu.nju.tickets.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * User Controller£¨µÇÂ¼¡¢×¢²á£©
 */

@Controller
public class UserController {
	private static final long serialVersionUID = 1L;

	
	
	
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getRegister() {
        return "index";
    }


    @RequestMapping(value = "/Main", method = RequestMethod.POST)
    protected String doRegist(@RequestParam(value = "userName",required=false) String userName,
    		@RequestParam(value = "passWord", required = false) String passWord,
    		HttpServletRequest request) {

		return "login";
	}
}
