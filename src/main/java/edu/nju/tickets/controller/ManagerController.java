package edu.nju.tickets.controller;


import edu.nju.tickets.dao.ManagerDao;
import edu.nju.tickets.dao.ManagerDataDao;
import edu.nju.tickets.model.*;
import edu.nju.tickets.model.util.ResultMessage;
import edu.nju.tickets.service.ManagerService;
import edu.nju.tickets.service.PlaceService;
import edu.nju.tickets.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;


@Controller
public class ManagerController {
    @Autowired
    PlaceService placeService;

    @Autowired
    ManagerService managerService;

    @Autowired
    ManagerDataDao managerDataDao;


    @RequestMapping(value = "/managerCheck", method = RequestMethod.GET)
    public String managerCheck(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if ((session == null) || (session.getAttribute("manager") == null) || (!session.getAttribute("manager").equals("root"))) {
            return "user_null";
        }

        List<PrePlace> prePlaceList = placeService.getModifiedPlace();
        request.setAttribute("prePlaceList", prePlaceList);

        return "manager_check";
    }


    @RequestMapping(value = "/managerCheckSignUp", method = RequestMethod.GET)
    public String managerCheckSignUp(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if ((session == null) || (session.getAttribute("manager") == null) || (!session.getAttribute("manager").equals("root"))) {
            return "user_null";
        }

        List<Place> placeList = placeService.getUnCheckedPlace();
        request.setAttribute("placeList", placeList);

        return "manager_checkSignUp";
    }

    @RequestMapping(value = "/managerCalc", method = RequestMethod.GET)
    public String managerCalc(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if ((session == null) || (session.getAttribute("manager") == null) || (!session.getAttribute("manager").equals("root"))) {
            return "user_null";
        }

        List<PlaceAccount> placeAccountList = managerService.getPlaceCalc();
        request.setAttribute("placeAccountList", placeAccountList);
        return "manager_calc";
    }

    @RequestMapping(value = "/managerInfoPlace", method = RequestMethod.GET)
    public String managerInfo(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if ((session == null) || (session.getAttribute("manager") == null) || (!session.getAttribute("manager").equals("root"))) {
            return "user_null";
        }

        List<PlaceAccount> placeAccountList = managerService.getPlaceCalc();
        request.setAttribute("placeAccountList", placeAccountList);

        return "manager_info_place";
    }

    @RequestMapping(value = "/managerInfoUser", method = RequestMethod.GET)
    public String managerUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if ((session == null) || (session.getAttribute("manager") == null) || (!session.getAttribute("manager").equals("root"))) {
            return "user_null";
        }

        List<UserAccount> userAccountList = managerService.getUserCalc();
        request.setAttribute("userAccountList", userAccountList);

        return "manager_info_user";
    }

    @RequestMapping(value = "/managerInfoTickets", method = RequestMethod.GET)
    public String managerTickets(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if ((session == null) || (session.getAttribute("manager") == null) || (!session.getAttribute("manager").equals("root"))) {
            return "user_null";
        }


        TicketsAccount ticketsAccount = managerService.getTicketsCalc();
        request.setAttribute("ticketsAccount", ticketsAccount);

        return "manager_info_tickets";
    }

    @ResponseBody
    @RequestMapping(value = "/checkModify", method = RequestMethod.POST)
    protected String checkModify(@RequestParam(value = "prePlaceId",required=false) int prePlaceId) {
        ResultMessage resultMessage = placeService.checkPlaceModifyRequestByPrePlaceId(prePlaceId);
        return resultMessage.toString();
    }

    @ResponseBody
    @RequestMapping(value = "/unCheckModify", method = RequestMethod.POST)
    protected String unCheckModify(@RequestParam(value = "prePlaceId",required=false) int prePlaceId) {
        ResultMessage resultMessage = placeService.unCheckPlaceModifyRequestByPrePlaceId(prePlaceId);
        return resultMessage.toString();
    }

    @ResponseBody
    @RequestMapping(value = "/checkSignUp", method = RequestMethod.POST)
    protected String checkSignUp(@RequestParam(value = "placeId",required=false) int placeId) {
        ResultMessage resultMessage = placeService.checkPlaceSignUpRequest(placeId);
        return resultMessage.toString();
    }

    @ResponseBody
    @RequestMapping(value = "/unCheckSignUp", method = RequestMethod.POST)
    protected String unCheckSignUp(@RequestParam(value = "placeId",required=false) int placeId) {
        ResultMessage resultMessage = placeService.unCheckPlaceSignUpRequest(placeId);
        return resultMessage.toString();
    }

    @RequestMapping(value = "/managerDataProvince", method = RequestMethod.GET)
    public String managerDataProvince(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if ((session == null) || (session.getAttribute("manager") == null) || (!session.getAttribute("manager").equals("root"))) {
            return "user_null";
        }

        List<Object[]> list = managerDataDao.getProvinceIncome();
        request.setAttribute("provinceList", list);

        return "manager_data_province";
    }

    @RequestMapping(value = "/managerDataUser", method = RequestMethod.GET)
    public String managerDataUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if ((session == null) || (session.getAttribute("manager") == null) || (!session.getAttribute("manager").equals("root"))) {
            return "user_null";
        }

        List<Object[]> list = managerDataDao.getMonthUserNum();
        List<Integer> list1 = managerDataDao.getUserNum();
        request.setAttribute("userList", list);
        request.setAttribute("userList1", list1);

        return "manager_data_user";
    }


    @RequestMapping(value = "/managerDataPlaceIncome", method = RequestMethod.GET)
    public String managerDataPlaceIncome(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if ((session == null) || (session.getAttribute("manager") == null) || (!session.getAttribute("manager").equals("root"))) {
            return "user_null";
        }

        List<Object[]> list = managerDataDao.getHighPlaceIncome();
        List<Object[]> list1 = managerDataDao.getLowPlaceIncome();
        request.setAttribute("highIncomeList", list);
        request.setAttribute("lowIncomeList", list1);

        return "manager_data_placeincome";
    }

    @RequestMapping(value = "/managerDataMonthIncome", method = RequestMethod.GET)
    public String managerDataMonthIncome(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if ((session == null) || (session.getAttribute("manager") == null) || (!session.getAttribute("manager").equals("root"))) {
            return "user_null";
        }

        List<Object[]> list = managerDataDao.getMonthIncome();
        request.setAttribute("incomeList", list);

        return "manager_data_monthincome";
    }

    @RequestMapping(value = "/managerDataIntegral", method = RequestMethod.GET)
    public String managerDataIntegral(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if ((session == null) || (session.getAttribute("manager") == null) || (!session.getAttribute("manager").equals("root"))) {
            return "user_null";
        }

        List<Object[]> list = managerDataDao.getMonthIntegral();
        request.setAttribute("integralList", list);

        return "manager_data_integral";
    }
}
