package com.lmonkey.servlet.cart;

import com.lmonkey.service.LMONKEY_CARTDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther huijia
 * @Date 2021/7/30
 **/
@WebServlet("/cartshopdel")
public class CartShopDel extends HttpServlet {
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String esid = request.getParameter("esid");

        LMONKEY_CARTDao.getDeleteDD(Integer.parseInt(esid));


    }


}
