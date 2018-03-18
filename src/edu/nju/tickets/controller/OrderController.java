package edu.nju.tickets.controller;

import edu.nju.tickets.OrderVO;
import edu.nju.tickets.model.Coupon;
import edu.nju.tickets.model.Order;
import edu.nju.tickets.model.User;
import edu.nju.tickets.model.util.ResultMessage;
import edu.nju.tickets.service.CouponService;
import edu.nju.tickets.service.OrderService;
import edu.nju.tickets.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by foxwel on 2018/3/14.
 */
@Controller
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    UserService userService;

    @Autowired
    CouponService couponService;

    @RequestMapping(value = "/orderUnPaied", method = RequestMethod.GET)
    public String getOrderUnPaied(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if ((session == null) || (session.getAttribute("userName") == null)) {
            return "user_null";
        }
        List<OrderVO> orderList = orderService.getUnPaiedOrderVOList((String) session.getAttribute("userName"));
        request.setAttribute("orderList", orderList);

        User user = userService.getUser((String) session.getAttribute("userName"));
        request.setAttribute("user", user);

        List<Coupon> couponList = couponService.getByUserName((String) session.getAttribute("userName"));
        request.setAttribute("couponList", couponList);

        return "order_unPaied";
    }

    @RequestMapping(value = "/orderFinished", method = RequestMethod.GET)
    public String getOrderFinished(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if ((session == null) || (session.getAttribute("userName") == null)) {
            return "user_null";
        }
        List<OrderVO> orderList = orderService.getPaiedOrderVOList((String) session.getAttribute("userName"));
        request.setAttribute("orderList", orderList);

        User user = userService.getUser((String) session.getAttribute("userName"));
        request.setAttribute("user", user);

        return "order_finished";
    }

    @RequestMapping(value = "/orderOld", method = RequestMethod.GET)
    public String getOrderOld(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        if ((session == null) || (session.getAttribute("userName") == null)) {
            return "user_null";
        }

        List<OrderVO> orderList = orderService.getOldOrderVOList((String) session.getAttribute("userName"));
        request.setAttribute("orderList", orderList);

        User user = userService.getUser((String) session.getAttribute("userName"));
        request.setAttribute("user", user);

        return "order_old";
    }


    @ResponseBody
    @RequestMapping(value = "/payOrder", method = RequestMethod.POST)
    protected String payOrder(@RequestParam(value = "userName",required=false) String userName,
                                    @RequestParam(value = "orderId", required = false) int orderId,
                                    @RequestParam(value = "payAccount", required = false) String payAccount,
                              @RequestParam(value = "payAccountPassWord", required = false) String payAccountPassWord) {
        ResultMessage resultMessage = orderService.payOrder(userName, orderId, payAccount, payAccountPassWord);
        return resultMessage.toString();
    }

    @ResponseBody
    @RequestMapping(value = "/cancelUnPaiedOrder", method = RequestMethod.POST)
    protected String cancelUnPaiedOrder(@RequestParam(value = "orderId", required = false) int orderId) {
        ResultMessage resultMessage = orderService.cancelOrder(orderId);
        return resultMessage.toString();
    }

    @ResponseBody
    @RequestMapping(value = "/cancelPaiedOrder", method = RequestMethod.POST)
    protected String cancelPaiedOrder(@RequestParam(value = "orderId", required = false) int orderId,
                                      @RequestParam(value = "payAccountId", required = false) String payAccountId) {
        ResultMessage resultMessage = orderService.cancelOrder(orderId, payAccountId);
        return resultMessage.toString();
    }

}
