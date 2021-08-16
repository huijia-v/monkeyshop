package com.lmonkey.servlet.user;

import com.lmonkey.enity.LMONKEY_USER;
import com.lmonkey.service.LMONKEY_USERDao;

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
@WebServlet("/manage/adminlogin")
public class AdminLogin extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //这是字符集
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        String username = request.getParameter("userName");
        String passwd = request.getParameter("passwd");

        int count = LMONKEY_USERDao.selectByNM(username, passwd);

        if (count > 0) {

            LMONKEY_USER user = LMONKEY_USERDao.selectAdmin(username, passwd);

            HttpSession session = request.getSession();
            session.setAttribute("name", user);
            session.setAttribute("islogin", "1");
            if (user.getUser_status() == 2) {


                session.setAttribute("isAdminLogin", "1");
                response.sendRedirect("/monkeyShop/manage/admin_index.jsp");

            } else {
                response.sendRedirect("/monkeyShop/index.jsp");
            }



        } else {
            PrintWriter out = response.getWriter();
            out.write("<script>");
            out.write("alert('用户登录失败!');");
            out.write("location.href='login.jsp';");
            out.write("</script>");
            out.close();
        }
    }
}
