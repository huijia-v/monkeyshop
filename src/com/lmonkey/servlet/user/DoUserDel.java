package com.lmonkey.servlet.user;

import com.lmonkey.service.LMONKEY_USERDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.ServerException;

/**
 * @Auther huijia
 * @Date 2021/7/21
 **/
@WebServlet("/manage/admin_douserdel")
public class DoUserDel extends HttpServlet {

     @Override
     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServerException, IOException {
         //这是字符集
         request.setCharacterEncoding("UTF-8");
         response.setContentType("text/html;charset=utf-8");

         String id = request.getParameter("id");






         //加入到数据库的用户表中

         int count = LMONKEY_USERDao.del(id);


         //去向成功或失败重定向到哪里

         if (count > 0) {
             response.sendRedirect("admin_douserselect?cp=" + request.getParameter("cpage"));

         }else {
             PrintWriter out = response.getWriter();
             out.write("<script>");
             out.write("alert('用户删除失败')");
             out.write("location.href='admin_douserselect?cp=" + request.getParameter("cpage") + "'");
             out.write("<script>");
         }
     }


     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
         //这是字符集
         request.setCharacterEncoding("UTF-8");
         response.setContentType("text/html;charset=utf-8");

         String[] ids = request.getParameterValues("id[]");

         //加入到数据库的用户表中

         for (int i = 0; i < ids.length; i++) {
             int count = LMONKEY_USERDao.del(ids[i]);

         }


         //去向成功或失败重定向到哪里


         response.sendRedirect("/monkeyShop/manage/admin_douserselect");

     }


}
