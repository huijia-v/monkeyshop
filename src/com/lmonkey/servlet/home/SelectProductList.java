package com.lmonkey.servlet.home;

import com.lmonkey.enity.LMONKEY_CATEGORY;
import com.lmonkey.enity.LMONKEY_PRODUCT;
import com.lmonkey.service.LMONKEY_CATEGORYDao;
import com.lmonkey.service.LMONKEY_PRODUCTDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @Auther huijia
 * @Date 2021/7/27
 **/

@WebServlet("/selectproductlist")
public class SelectProductList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<LMONKEY_CATEGORY> flist = LMONKEY_CATEGORYDao.selectCat("father");
        request.setAttribute("flist", flist);
        ArrayList<LMONKEY_CATEGORY> clist = LMONKEY_CATEGORYDao.selectCat("child");
        request.setAttribute("clist", clist);

        String fid = request.getParameter("fid");
        String cid = request.getParameter("cid");

        int id = 0;
        ArrayList<LMONKEY_PRODUCT> list = null;
        if (fid != null) {
            id = Integer.parseInt(fid);
            list = LMONKEY_PRODUCTDao.selectByAllFid(id);
        }
        if (cid != null) {
            id = Integer.parseInt(cid);
            list = LMONKEY_PRODUCTDao.selectByAllCid(id);
        }

        request.setAttribute("title", LMONKEY_CATEGORYDao.selectById(id).getCate_name());
        request.setAttribute("list", list);

        request.getRequestDispatcher("productlist.jsp").forward(request, response);
    }
}
