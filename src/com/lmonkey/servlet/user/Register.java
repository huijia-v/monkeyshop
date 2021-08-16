package com.lmonkey.servlet.user;

import com.lmonkey.enity.LMONKEY_USER;
import com.lmonkey.service.LMONKEY_USERDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Auther huijia
 * @Date 2021/7/23
 **/

@WebServlet("/register")
public class Register extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //这是字符集
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        String username = request.getParameter("userName");
        String name = request.getParameter("name");
        String pwd  = request.getParameter("passwd");
        String sex = request.getParameter("sex");
        String year = request.getParameter("birthday");
        String email = request.getParameter("email");
        String mobbile = request.getParameter("mobile");
        String address = request.getParameter("address");

        //创建用户实体
        LMONKEY_USER u = new LMONKEY_USER(username, name, pwd, sex, year, null, email, mobbile, address, 1);


        //加入到数据库的用户表中

        int count = LMONKEY_USERDao.insert(u);
//        System.out.println(u);


        //去向成功或失败重定向到哪里

        if (count > 0) {
            response.sendRedirect("login.jsp");
        }else {
            PrintWriter out = response.getWriter();
            out.write("<script>");
            out.write("alert('用户注册失败');");
            out.write("location.href='reg.jsp';");
            out.write("<script>");
        }

    }
}
