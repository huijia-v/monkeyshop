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
 * @Date 2021/7/24
 **/

@WebServlet("/login")
public class Login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //这是字符集
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        String username = request.getParameter("userName");
        String passwd = request.getParameter("passwd");

        int count = LMONKEY_USERDao.selectByNM(username, passwd);

        if (count > 0) {
            HttpSession session = request.getSession();
            LMONKEY_USER user = LMONKEY_USERDao.selectAdmin(username, passwd);

            session.setAttribute("name", user);
            session.setAttribute("islogin", "1");
            response.sendRedirect("indexselect");

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
