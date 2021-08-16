package com.lmonkey.servlet.cate;

import com.lmonkey.service.LMONKEY_CATEGORYDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther huijia
 * @Date 2021/7/26
 **/
@WebServlet("/manage/admin_docatedel")
public class DoCateDel extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        LMONKEY_CATEGORYDao.del(id);
        response.sendRedirect("admin_cateselect");

    }
}
