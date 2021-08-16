package com.lmonkey.servlet.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Auther huijia
 * @Date 2021/7/25
 **/
@WebServlet("/manage/admin_logout")
public class AdminLogout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //这是字符集
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        HttpSession session = request.getSession();
        session.removeAttribute("name");
        session.removeAttribute("islogin");
        session.removeAttribute("isAdminLogin");

        PrintWriter out = response.getWriter();
        out.write("<script>");
        out.write("alert('退出成功！');");
        out.write("location.href='login.jsp';");
        out.write("</script>");
    }
}
