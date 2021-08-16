package com.lmonkey.servlet.user;

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


@WebServlet("/usernamecheck")
public class UserNameCheck extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置字符集
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        String name = request.getParameter("name");

        int count = LMONKEY_USERDao.selectByName(name);

        PrintWriter out = response.getWriter();
        if (count > 0) {
            out.print("false");
        } else {
            out.println("true");

        }
        out.close();
    }

}
