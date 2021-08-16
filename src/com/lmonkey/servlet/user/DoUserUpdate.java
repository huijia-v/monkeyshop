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
 * @Date 2021/7/20
 **/

@WebServlet("/manage/douserupdate")
public class DoUserUpdate extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //这是字符集
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        String username = request.getParameter("userName");
        String name = request.getParameter("name");
        String pwd  = request.getParameter("passwd");
        String sex = request.getParameter("sex");
        String birthday = request.getParameter("birthday");
        String email = request.getParameter("email");
        String mobbile = request.getParameter("mobile");
        String address = request.getParameter("address");
        String userStatus = request.getParameter("userStatus");

        int status = 1;
        if (userStatus != null) {
            status = Integer.parseInt(userStatus);
        }

        //创建用户实体
        LMONKEY_USER user = new LMONKEY_USER(username, name, pwd, sex, birthday, null, email, mobbile, address, status);


        //加入到数据库的用户表中

        int count = LMONKEY_USERDao.update(user);
//        System.out.println(u);


        //去向成功或失败重定向到哪里

        if (count > 0) {
            response.sendRedirect("admin_douserselect?cp=" + request.getParameter("cpage"));

        }else {
            PrintWriter out = response.getWriter();
            out.write("<script>");
            out.write("alert('用户修改失败')");
            out.write("location.href='manage/admin_touserupdate?id=" + username +"'");
            out.write("<script>");
        }
    }


}
