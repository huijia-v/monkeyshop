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

@WebServlet("/manage/admin_docateupdate")
public class DocateUpdate extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //这是字符集
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        int id = Integer.parseInt(request.getParameter("id"));
        int fid = Integer.parseInt(request.getParameter("parentId"));
        String name = request.getParameter("className");

        LMONKEY_CATEGORY cate = new LMONKEY_CATEGORY(id, name, fid);
        LMONKEY_CATEGORYDao.update(cate);
        response.sendRedirect("admin_cateselect");
    }
}
