package edu.nju.tickets.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by foxwel on 2018/3/14.
 */
@Controller
public class OrderController {

    @RequestMapping(value = "/orderUnPaied", method = RequestMethod.GET)
    public String getRegister() {
        return "order_unPaied";
    }

    @RequestMapping(value = "/orderFinished", method = RequestMethod.GET)
    public String getUserInfo() {
        return "order_finished";
    }

    @RequestMapping(value = "/orderOld", method = RequestMethod.GET)
    public String getUserChangePassWord() {
        return "order_old";
    }

}
