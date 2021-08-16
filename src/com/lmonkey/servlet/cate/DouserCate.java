package com.lmonkey.servlet.cate;

import com.lmonkey.enity.LMONKEY_CATEGORY;
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

@WebServlet("/manage/admin_docateadd")
public class DouserCate extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //这是字符集
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        int fid = Integer.parseInt(request.getParameter("parentId"));
        String name = request.getParameter("className");
        LMONKEY_CATEGORY cate = new LMONKEY_CATEGORY(0, name, fid);
        LMONKEY_CATEGORYDao.insert(cate);
        response.sendRedirect("admin_cateselect");
    }
}
