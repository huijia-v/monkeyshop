package com.lmonkey.servlet.cate;

import com.lmonkey.enity.LMONKEY_CATEGORY;
import com.lmonkey.service.LMONKEY_CATEGORYDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @Auther huijia
 * @Date 2021/7/26
 **/
@WebServlet("/manage/admin_tocateupdate")
public class ToCateUpdate extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        LMONKEY_CATEGORY cate = LMONKEY_CATEGORYDao.selectById(id);

        ArrayList<LMONKEY_CATEGORY> catelist = LMONKEY_CATEGORYDao.selectAll();

        request.setAttribute("catelist", catelist);
        request.setAttribute("cate1", cate);
        request.getRequestDispatcher("admin_catemodify.jsp").forward(request, response);
    }
}
