package edu.nju.tickets.servlets;

import edu.nju.tickets.model.OldOrder;
import edu.nju.tickets.model.OrderListBean;
import edu.nju.tickets.model.util.ResultMessage;
import edu.nju.tickets.service.UserService;
import edu.nju.tickets.service.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


public class MainServlet extends HttpServlet {

    private static ApplicationContext appliationContext;

    private static UserService userService;

    private static OrderService orderService;

    public void init() throws ServletException {
        super.init();
        appliationContext = new ClassPathXmlApplicationContext("WEB-INF/applicationContext.xml");
        userService = (UserService) appliationContext.getBean("userServiceImpl");
        orderService = (OrderService) appliationContext.getBean("orderServiceImpl");
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            String userName = (String)session.getAttribute("userName");
            if (null != request.getParameter("pageID")) {
                getOrderTable(request, response, userName, Integer.valueOf(request.getParameter("pageID")));
            } else {
                getOrderTable(request, response, userName, 1);
            }
        } else {
            response.sendRedirect("/Login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext Context= getServletContext();
        int webLoginCounter = Integer.parseInt((String) Context.getAttribute("webLoginCounter"));
        webLoginCounter++;
        Context.setAttribute("webLoginCounter", Integer.toString(webLoginCounter));

        HttpSession session = request.getSession(false);

        String userName = request.getParameter("userName");
        String passWord = request.getParameter("passWord");

        System.out.println(userName);
        Cookie newCookie = new Cookie("userName", URLEncoder.encode(userName, "UTF-8"));
        response.addCookie(newCookie);

        ResultMessage resultMessage = userService.login(userName, passWord);

        System.out.println("result: " + resultMessage);
        if (resultMessage == ResultMessage.SUCCESS) {
            session = request.getSession(true);
            session.setAttribute("userName", userName);
            getOrderTable(request, response, userName, 1);
        } else {
            if (session != null) {
                session.invalidate();
            }
            response.sendRedirect("/Warning");
        }

    }

    private int min(int a, int b) {
        if (a < b) return a; else return b;
    }

    private void getOrderTable(HttpServletRequest request, HttpServletResponse response, String userName, int pageID) throws IOException {
        ServletContext context = getServletContext();


        List<OldOrder> orderList = orderService.getOrderList(userName);

        int orderNum = orderList.size();
        int pageNum = ((orderNum - 1) / 5) + 1;

        List<OldOrder> newOrderList = new ArrayList<>();

        for (int i = 5 * (pageID - 1); i < min(5 * pageID, orderList.size()); ++i) {
            newOrderList.add(orderList.get(i));
        }

        OrderListBean orderListBean = new OrderListBean();
        orderListBean.setOrderList(newOrderList);
        request.setAttribute("orderList", orderListBean);
        request.setAttribute("userName", userName);

        request.setAttribute("pageNum", pageNum);
        request.setAttribute("pageID", pageID);


        int webCounter = Integer.parseInt((String) context.getAttribute("webCounter"));
        int webLoginCounter = Integer.parseInt((String) context.getAttribute("webLoginCounter"));

        request.setAttribute("allNum", webCounter);
        request.setAttribute("loginNum", webLoginCounter);

        request.setAttribute("otherNum", (webCounter - webLoginCounter));
        try {
            context.getRequestDispatcher("/order/listOrder.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "This is a ServletException.");
        }

    }
}
