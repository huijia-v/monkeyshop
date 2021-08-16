package com.lmonkey.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Auther huijia
 * @Date 2021/7/23
 **/
@WebFilter("/register")
public class Register implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        //设置字符集
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");

        String userName = req.getParameter("userName");
        PrintWriter out = resp.getWriter();
        if (userName.equals("")) {
            out.write("<script>");
            out.write("alert('用户名称不能为空');");
            out.write("location.href='reg.jsp';");
            out.write("</script>");
            out.close();
            return;
        }

        HttpSession session = req.getSession();
        String code = req.getParameter("code");
        String syscode = (String) session.getAttribute("code");
        if (!syscode.equals(code)) {
            out.write("<script>");
            out.write("alert('验证码输入有误!');");
            out.write("location.href='reg.jsp';");
            out.write("</script>");
            out.close();
            return;
        }
        chain.doFilter(req, resp);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }



    @Override
    public void destroy() {

    }


}
