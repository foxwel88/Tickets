package edu.nju.tickets.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * User Controller（登录、注册）
 */

@Controller //@Controller用于标注控制层组件(如struts中的action)
public class UserController {
	private static final long serialVersionUID = 1L;

	
	
	
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getRegister() {
        return "index";
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    protected String doRegist(@RequestParam("year") String yearS,
    		@RequestParam("month") String monthS,
    		@RequestParam("day") String dayS,
    		@RequestParam("passwordOne") String ps1,
    		@RequestParam("passwordTwo") String ps2,
    		ModelMap model,
    		HttpServletRequest request) {

		return "RegUser";
	}
}
