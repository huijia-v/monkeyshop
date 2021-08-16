package com.lmonkey.servlet.cart;

import com.lmonkey.enity.LMONKEY_CART;
import com.lmonkey.enity.LMONKEY_USER;
import com.lmonkey.service.LMONKEY_CARTDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * @Auther huijia
 * @Date 2021/7/31
 **/
@WebServlet("/orderselect")
public class OrderSelect extends HttpServlet {

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        HttpSession session = request.getSession();

        String isLogin = (String)session.getAttribute("islogin");

        LMONKEY_USER user =(LMONKEY_USER)session.getAttribute("name");

        String eids = request.getParameter("eids");

        if(user!=null && isLogin.equals("1")) {

            int totalprice = 0;


            String ids[] = eids.split(",");

            ArrayList<LMONKEY_CART> list = new ArrayList<>();

            for(int i=0; i<ids.length; i++) {
                LMONKEY_CART es = LMONKEY_CARTDao.getCartShop(ids[i]);

                int dprice = es.getCart_p_price() * es.getCart_quantity();

                totalprice += dprice;

                list.add(es);
            }
            request.setAttribute("shoplist", list);

            request.setAttribute("totalprice", totalprice);

            request.getRequestDispatcher("order.jsp").forward(request, response);
        }else {
            PrintWriter out = response.getWriter();
            out.write("<script>");
            out.write("alter('用户登录失败');");
            out.write("location.href='login.jsp';");
            out.write("</script>");
            out.close();
            return;
        }

    }

}

