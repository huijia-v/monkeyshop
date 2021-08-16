package com.lmonkey.servlet.home;

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

@WebServlet("/indexselect")
public class IndexSelect extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ArrayList<LMONKEY_CATEGORY> flist = LMONKEY_CATEGORYDao.selectCat("father");
        request.setAttribute("flist", flist);
        ArrayList<LMONKEY_CATEGORY> clist = LMONKEY_CATEGORYDao.selectCat("child");
        request.setAttribute("clist", clist);
        request.getRequestDispatcher("index.jsp").forward(request, response);

    }
}
