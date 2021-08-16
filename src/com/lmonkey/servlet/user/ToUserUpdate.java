package com.lmonkey.servlet.user;

import com.lmonkey.enity.LMONKEY_USER;
import com.lmonkey.service.LMONKEY_USERDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther huijia
 * @Date 2021/7/20
 **/


@WebServlet("/manage/admin_touserupdate")
public class ToUserUpdate extends HttpServlet {
    private static final long serialVersionUID = 1L;



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        String id = request.getParameter("id");
        //通过id到数据里查找
        LMONKEY_USER user = LMONKEY_USERDao.selectById(id);

        request.setAttribute("user", user);
        request.setAttribute("cpage",request.getParameter("cpage"));
        request.getRequestDispatcher("admin_usermodify.jsp").forward(request, response);
    }


}
