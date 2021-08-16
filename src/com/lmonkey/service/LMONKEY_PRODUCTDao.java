package com.lmonkey.service;

/**
 * @Auther huijia
 * @Date 2021/7/27
 **/

import com.lmonkey.dao.BaseDao;
import com.lmonkey.enity.LMONKEY_PRODUCT;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LMONKEY_PRODUCTDao {
    public static int insert(LMONKEY_PRODUCT p) {

        String sql = "insert into lmonkey_product values(null,?, ?, ?, ?, ?, ?, ?)";

        Object[] params= {
                p.getProduct_name(),
                p.getProduct_description(),
                p.getProduct_price(),
                p.getProduct_stock(),
                p.getProduct_fid(),
                p.getProduct_cid(),
                p.getProduct_filename()

        };
        return BaseDao.exectuIUD(sql, params);
    }


    public static ArrayList<LMONKEY_PRODUCT> selectAll() {
        ArrayList<LMONKEY_PRODUCT> list = new ArrayList<>();
        ResultSet rs = null;//声明结果集
        //获取连接对象
        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;

        try {
            String sql = "select * from lmonkey_product ";
            ps = conn.prepareStatement(sql);

            rs=ps.executeQuery();

            while(rs.next()) {
                LMONKEY_PRODUCT p = new LMONKEY_PRODUCT(
                        rs.getInt("product_id"),
                        rs.getString("product_name"),
                        rs.getString("production_description"),
                        rs.getInt("product_price"),
                        rs.getInt("product_stock"),
                        rs.getInt("product_fid"),
                        rs.getInt("product_cid"),
                        rs.getString("product_filename")
                );
                list.add(p);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            BaseDao.closeall(rs, ps, conn);
        }
        return list;
    }

    public static ArrayList<LMONKEY_PRODUCT> selectByAllFid(int fid) {
        ArrayList<LMONKEY_PRODUCT> list=new ArrayList<LMONKEY_PRODUCT>();
        ResultSet rs = null;//声明结果集
        //获取连接对象
        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;

        try {
            String sql = "select * from lmonkey_product where PRODUCT_FID=?";
            ps = conn.prepareStatement(sql);

            ps.setInt(1, fid);


            rs=ps.executeQuery();

            while(rs.next()) {
                LMONKEY_PRODUCT p = new LMONKEY_PRODUCT(
                        rs.getInt("product_id"),
                        rs.getString("product_name"),
                        rs.getString("production_description"),
                        rs.getInt("product_price"),
                        rs.getInt("product_stock"),
                        rs.getInt("product_fid"),
                        rs.getInt("product_cid"),
                        rs.getString("product_filename")
                );
                list.add(p);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            BaseDao.closeall(rs, ps, conn);
        }
        return list;
    }

    public static ArrayList<LMONKEY_PRODUCT> selectByAllCid(int cid) {
        ArrayList<LMONKEY_PRODUCT> list= new ArrayList<>();
        ResultSet rs = null;//声明结果集
        //获取连接对象
        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;

        try {
            String sql = "select * from lmonkey_product where product_cid=?";
            ps = conn.prepareStatement(sql);

            ps.setInt(1, cid);


            rs=ps.executeQuery();

            while(rs.next()) {
                LMONKEY_PRODUCT p = new LMONKEY_PRODUCT(
                        rs.getInt("product_id"),
                        rs.getString("product_name"),
                        rs.getString("production_description"),
                        rs.getInt("product_price"),
                        rs.getInt("product_stock"),
                        rs.getInt("product_fid"),
                        rs.getInt("product_cid"),
                        rs.getString("product_filename")

                );
                list.add(p);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            BaseDao.closeall(rs, ps, conn);
        }
        return list;
    }

    public static LMONKEY_PRODUCT selectById(int id) {
        LMONKEY_PRODUCT p = null;
        ResultSet rs = null;//声明结果集
        //获取连接对象
        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;

        try {
            String sql = "select * from lmonkey_product where product_id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs=ps.executeQuery();

            while(rs.next()) {
                p = new LMONKEY_PRODUCT(
                        rs.getInt("product_id"),
                        rs.getString("product_name"),
                        rs.getString("production_description"),
                        rs.getInt("product_price"),
                        rs.getInt("product_stock"),
                        rs.getInt("product_fid"),
                        rs.getInt("product_cid"),
                        rs.getString("product_filename")
                );

            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            BaseDao.closeall(rs, ps, conn);
        }
        return p;
    }


    public static ArrayList<LMONKEY_PRODUCT> selectAllById(ArrayList<Integer> ids) {
        ArrayList<LMONKEY_PRODUCT> lastlylist= new ArrayList<>();

        LMONKEY_PRODUCT p = null;

        ResultSet rs = null;//声明结果集
        //获取连接对象
        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;

        try {
            for(int i=0; i<ids.size(); i++) {
                String sql = "select * from lmonkey_product where PRODUCT_ID=?";
                ps = conn.prepareStatement(sql);

                ps.setInt(1, ids.get(i));


                rs=ps.executeQuery();

                while(rs.next()) {
                    p = new LMONKEY_PRODUCT(
                            rs.getInt("product_id"),
                            rs.getString("product_name"),
                            rs.getString("production_description"),
                            rs.getInt("product_price"),
                            rs.getInt("product_stock"),
                            rs.getInt("product_fid"),
                            rs.getInt("product_cid"),
                            rs.getString("product_filename")



                    );
                    lastlylist.add(p);
                }
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            BaseDao.closeall(rs, ps, conn);
        }
        return lastlylist;
    }



    /**
     * 删除图书
     * @param id
     * @return
     */
    public static int del(int id) {
        String sql = "delete from product where PRODUCT_ID=?";
        Object[] params = {id};
        return BaseDao.exectuIUD(sql, params);
    }



    /**
     *
     * @param id
     * @return
     */
    public static LMONKEY_PRODUCT selectByID(int id) {
        LMONKEY_PRODUCT p = null;

        ResultSet rs = null;//声明结果集
        //获取连接对象
        Connection conn = BaseDao.getconn();

        PreparedStatement ps = null;

        try {
            String sql = "select * from lmonkey_product where PRODUCT_ID=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs=ps.executeQuery();


            while(rs.next()) {
                p = new LMONKEY_PRODUCT(
                        rs.getInt("product_id"),
                        rs.getString("product_name"),
                        rs.getString("production_description"),
                        rs.getInt("product_price"),
                        rs.getInt("product_stock"),
                        rs.getInt("product_fid"),
                        rs.getInt("product_cid"),
                        rs.getString("product_filename")
                );

            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            BaseDao.closeall(rs, ps, conn);
        }
        return p;
    }

}
