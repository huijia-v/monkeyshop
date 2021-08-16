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
 * @Date 2021/7/30
 **/
@WebServlet("/showcart")
public class ShowCart extends HttpServlet {

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

        if(user!=null && isLogin.equals("1")) {
            String uid = (String) user.getUser_id();

            ArrayList<LMONKEY_CART> list = LMONKEY_CARTDao.getCart(uid);

            request.setAttribute("shoplist", list);

            request.getRequestDispatcher("cart.jsp").forward(request, response);

        }else {
            PrintWriter out = response.getWriter();
            out.write("<script>");
            out.write("alter('用户登录失败')");
            out.write("location.href='login.jsp'");
            out.write("</script>");
            out.close();
            return;
        }

    }


}
