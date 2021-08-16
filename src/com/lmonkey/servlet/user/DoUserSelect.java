package com.lmonkey.servlet.user;

import com.lmonkey.enity.LMONKEY_USER;
import com.lmonkey.service.LMONKEY_USERDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @Auther huijia
 * @Date 2021/7/17
 **/

@WebServlet("/manage/admin_douserselect")
public class DoUserSelect extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        int cpage = 1;
        int count = 5;

        //获取用户指定的页面
        String cp = request.getParameter("cp");


        //接受用户搜索的关键字
        String keyWords = request.getParameter("keywords");


        if (cp != null) {
            cpage = Integer.parseInt(cp);
        }


        int arr[] = LMONKEY_USERDao.totalPage(count,keyWords);


        //获取所有的用户记录
        ArrayList<LMONKEY_USER> list = LMONKEY_USERDao.selectAll(cpage,count,keyWords);

//        System.out.println(list.toString());

        //放到请求的对象域里
        request.setAttribute("userlist", list);
        request.setAttribute("tsum", arr[0]);
        request.setAttribute("tpage", arr[1]);
        request.setAttribute("cpage", cpage);

        if (keyWords != null) {
            request.setAttribute("searchParams", "&keywords="+keyWords);
        }
        request.getRequestDispatcher("admin_user.jsp").forward(request, response);
    }


}
