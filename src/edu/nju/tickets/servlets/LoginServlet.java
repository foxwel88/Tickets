package edu.nju.tickets.servlets;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//默认用户名 root 密码 123456

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String cookieUserName="";
		HttpSession session = request.getSession(false);
        Cookie[] cookies = request.getCookies();

        if (null != cookies) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (cookie.getName().equals("userName")) {
                    cookieUserName = URLDecoder.decode(cookie.getValue(), "UTF-8");
                    break;
                }
            }
        }

        ServletContext Context= getServletContext();
        int webCounter = Integer.parseInt((String) Context.getAttribute("webCounter"));
        int webLoginCounter = Integer.parseInt((String) Context.getAttribute("webLoginCounter"));

        if (null != request.getParameter("logout")) {
            if (null != session) {
                webLoginCounter--;
                Context.setAttribute("webLoginCounter", Integer.toString(webLoginCounter));
                session.invalidate();
            }
        } else {
            webCounter++;
            System.out.println("mygod");
            Context.setAttribute("webCounter", Integer.toString(webCounter));
        }

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<form method='POST' action='" + response.encodeURL(request.getContextPath()) + "/Main" + "'>");
        out.println("用户名: <input type='text' name='userName' value='" + cookieUserName + "'>");
        out.println("密码: <input type='password' name='passWord' value=''>");
        out.println("<input type='submit' name='Submit' value='登陆'>");
        out.println("</p>总人数 " + webCounter);
        out.println("</p>登陆人数 " + webLoginCounter);
        out.println("</p>游客人数 " + (webCounter - webLoginCounter));
        out.println("</form></body></html>");
     
	}

}
