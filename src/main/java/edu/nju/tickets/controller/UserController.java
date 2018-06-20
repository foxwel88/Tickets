package edu.nju.tickets.controller;


import edu.nju.tickets.dao.UserDataDao;
import edu.nju.tickets.model.Coupon;
import edu.nju.tickets.model.User;
import edu.nju.tickets.model.util.ResultMessage;
import edu.nju.tickets.service.CouponService;
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
import java.util.Date;
import java.util.List;


@Controller
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    CouponService couponService;

    @Autowired
    UserDataDao userDataDao;

	private static final long serialVersionUID = 1L;

	
	


    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public String getUserInfo(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            String userName = (String)session.getAttribute("userName");
            if (userName != null) {
                User user = userService.getUser(userName);
                request.setAttribute("user", user);

                List<Coupon> couponList = couponService.getByUserName(userName);
                request.setAttribute("couponList", couponList);
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

    @RequestMapping(value = "/userGetCoupon", method = RequestMethod.GET)
    public String getUserGetCoupon(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            String userName = (String)session.getAttribute("userName");
            if (userName != null) {
                User user = userService.getUser(userName);
                request.setAttribute("user", user);
                return "user_getCoupon";
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

    @ResponseBody
    @RequestMapping(value = "/modifyUserInfo", method = RequestMethod.POST)
    protected String modifyUserInfo(@RequestParam(value = "userName",required=false) String userName,
                                    @RequestParam(value = "phoneNumber", required = false) String phoneNumber,
                                    @RequestParam(value = "userAddress", required = false) String userAddress) {
        User user = userService.getUser(userName);
        user.setPhone(phoneNumber);
        user.setAddress(userAddress);
        ResultMessage resultMessage = userService.modify(user);
        return resultMessage.toString();
    }

    @ResponseBody
    @RequestMapping(value = "/cancelUser", method = RequestMethod.POST)
    protected String cancelUser(@RequestParam(value = "userName",required=false) String userName,
                                    @RequestParam(value = "passWord", required = false) String passWord) {

        ResultMessage resultMessage = userService.cancel(userName, passWord);
        return resultMessage.toString();
    }

    @ResponseBody
    @RequestMapping(value = "/getCoupon", method = RequestMethod.POST)
    protected String getCoupon(@RequestParam(value = "userName",required=false) String userName,
                                @RequestParam(value = "couponId", required = false) int couponId) {

        if (couponId == 1) {
            Coupon coupon = new Coupon(userName, 500, 20, new Date("2018/10/10"));
            couponService.add(coupon);
            userService.addIntegral(userName, -100);
        } else {
            Coupon coupon = new Coupon(userName, 300, 20, new Date("2018/10/10"));
            couponService.add(coupon);
            userService.addIntegral(userName, -300);
        }
        return "SUCCESS";
    }


    @RequestMapping(value = "/userDataIncome", method = RequestMethod.GET)
    public String userDataIncome(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            String userName = (String)session.getAttribute("userName");
            if (userName != null) {
                List<Object[]> list = userDataDao.getPlaceIncome(userName);
                request.setAttribute("incomeList", list);
                return "user_data_income";
            } else {
                return "user_null";
            }
        } else {
            return "user_null";
        }

    }

    @RequestMapping(value = "/userDataIntegral", method = RequestMethod.GET)
    public String userDataIntegral(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            String userName = (String)session.getAttribute("userName");
            if (userName != null) {
                List<Object[]> list = userDataDao.getPlaceMonthIntegral(userName);
                request.setAttribute("integralList", list);
                return "user_data_integral";
            } else {
                return "user_null";
            }
        } else {
            return "user_null";
        }
    }

}
