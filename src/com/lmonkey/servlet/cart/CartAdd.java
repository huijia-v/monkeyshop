package com.lmonkey.servlet.cart;

import com.lmonkey.enity.LMONKEY_CART;
import com.lmonkey.enity.LMONKEY_PRODUCT;
import com.lmonkey.enity.LMONKEY_USER;
import com.lmonkey.service.LMONKEY_CARTDao;
import com.lmonkey.service.LMONKEY_PRODUCTDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Auther huijia
 * @Date 2021/7/29
 **/
@WebServlet("/cartadd")
public class CartAdd extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        LMONKEY_PRODUCT p = null;
        String pid = request.getParameter("id");
        String count = request.getParameter("count");
        String url = request.getParameter("url");

        HttpSession session = request.getSession();

        String isLogin = (String)session.getAttribute("islogin");

        LMONKEY_USER user =(LMONKEY_USER)session.getAttribute("name");

        if(user!=null && isLogin.equals("1")) {

            String uid = user.getUser_id();

            //通过用户ID和购物车中的商品ID 查看有没有这条记录

            LMONKEY_CART srcsp = LMONKEY_CARTDao.getCartShop(uid, pid);

            if(srcsp != null) {
                int srccount = srcsp.getCart_quantity();  //拿到原数量
                int newcount = srccount + Integer.parseInt(count);  //创建新的数量

                if(newcount >=5) {
                    newcount =5;
                }
                LMONKEY_CARTDao.updatenum(srcsp.getCart_id(), newcount);

            }else {

                if(pid !=null) {
                    p = LMONKEY_PRODUCTDao.selectById(Integer.parseInt(pid));
                }

                LMONKEY_CART cart = new LMONKEY_CART(
                        0,
                        p.getProduct_filename(),
                        p.getProduct_name(),
                        p.getProduct_price(),
                        Integer.parseInt(count),
                        p.getProduct_stock(),
                        p.getProduct_id(),
                        uid,
                        1
                );
                LMONKEY_CARTDao.insert(cart);
            }
        }else {
            PrintWriter out = response.getWriter();
            out.write("<script>");
            out.write("alter('登录后，后购买');");
            out.write("location.href='login.jsp';");
            out.write("</script>");
            out.close();
            return;
        }

        if(url.equals("z")) {
            response.sendRedirect("showcart");
        }else {
            response.sendRedirect("selectproductview?id="+pid);
        }
    }

}

