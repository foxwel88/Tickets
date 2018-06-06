package edu.nju.tickets.tag;

/**
 * Created by foxwel on 2018/1/5.
 */
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class CheckSessionHandler extends BodyTagSupport {

    private static final long serialVersionUID = 1L;


    @Override
    public int doStartTag() throws JspException {
        int result = 0;
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        HttpSession session = pageContext.getSession();
        if ((session == null) || (session.getAttribute("userName") == null)) {
            result = SKIP_BODY;
            try {
                HttpServletResponse response = (HttpServletResponse) pageContext.getResponse();
                response.sendRedirect("/Login");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            result = EVAL_BODY_INCLUDE;
        }
        return result;
    }

}
