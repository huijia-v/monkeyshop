package com.lmonkey.service;

import com.lmonkey.dao.BaseDao;
import com.lmonkey.enity.LMONKEY_CATEGORY;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @Auther huijia
 * @Date 2021/7/25
 **/
public class LMONKEY_CATEGORYDao {



    /*获取所有分类*/
    public static ArrayList<LMONKEY_CATEGORY> selectAll() {

        ArrayList<LMONKEY_CATEGORY> list = new ArrayList<>();


        //声明结果集
        ResultSet rs = null;
        //获取连接对象
        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;



        try {
            String sql = "select * from LMONKEY_CATEGORY ";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {

                LMONKEY_CATEGORY cate = new LMONKEY_CATEGORY(
                        rs.getInt("cate_id"),
                        rs.getString("cate_name"),
                        rs.getInt("cate_parent_id")
                );

                list.add(cate);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            BaseDao.closeall(rs, ps, conn);
        }

        return list;
    }




    /*查询分类，子分类和父级分类
    * flag flag="father" flag="child" */
    public static ArrayList<LMONKEY_CATEGORY> selectCat(String flag) {

        ArrayList<LMONKEY_CATEGORY> list = new ArrayList<>();


        //声明结果集
        ResultSet rs = null;
        //获取连接对象
        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;


        try {
            String sql = "";
            if (flag != null && flag.equals("father")) {
                sql = "select * from LMONKEY_CATEGORY where cate_parent_id=0 ";

            } else {
                sql = "select * from LMONKEY_CATEGORY where cate_parent_id != 0 ";
            }
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {

                LMONKEY_CATEGORY cate = new LMONKEY_CATEGORY(
                        rs.getInt("cate_id"),
                        rs.getString("cate_name"),
                        rs.getInt("cate_parent_id")
                );

                list.add(cate);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            BaseDao.closeall(rs, ps, conn);
        }

        return list;
    }

/*添加分类*/
    public static int insert(LMONKEY_CATEGORY cate) {
        String sql = "insert into LMONKEY_category values(null,?,?)";

        Object[] params = {
                cate.getCate_name(),
                cate.getCate_parent_id(),
        };

        int count = BaseDao.exectuIUD(sql, params);
        return count;
    }


    public static int update(LMONKEY_CATEGORY cate) {
//        String sql = "update  LMONKEY_USER set user_name=?,user_passwd=?,user_sex=?,user_birthday=DATE_FORMAT(?,'%y-%m-%d-), user_idenity_code=?,user_email=?,user_mobile=?,user_address=?,user_status=? where user_id=?";


        String sql = "update lmonkey_category set cate_name=?, cate_parent_id=? where cate_id=?";

        Object[] params = {
                cate.getCate_name(),
                cate.getCate_parent_id(),
                cate.getCate_id(),

        };

        return  BaseDao.exectuIUD(sql, params);

    }


    public static int del(int id) {
        String sql = "delete from lmonkey_category where cate_id=?";
        Object[] params = {id};
        return BaseDao.exectuIUD(sql, params);


    }


    //通过id查找用户
    public static LMONKEY_CATEGORY selectById(int id) {

        LMONKEY_CATEGORY cate = null;


        //声明结果集
        ResultSet rs = null;
        //获取连接对象
        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;



        try {
            String sql = "select * from LMONKEY_CATEGORY  where cate_id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);




            rs = ps.executeQuery();
            while (rs.next()) {

                cate = new LMONKEY_CATEGORY(
                        rs.getInt("cate_id"),
                        rs.getString("cate_name"),
                        rs.getInt("cate_parent_id")

                        );


            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            BaseDao.closeall(rs, ps, conn);
        }

        return cate;
    }
}
