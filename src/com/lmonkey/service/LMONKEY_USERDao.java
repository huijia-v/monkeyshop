package com.lmonkey.service;

import com.lmonkey.dao.BaseDao;
import com.lmonkey.enity.LMONKEY_USER;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @Auther huijia
 * 加入数据库 u
 * @Date 2021/7/17
 **/
public class LMONKEY_USERDao {

    public static int insert(LMONKEY_USER u) {
        String sql = "insert into LMONKEY_USER values(?,?,?,?,DATE_FORMAT(?, '%Y-%m-%d'),?,?,?,?,?)";

        Object[] params = {
                u.getUser_id(),
                u.getUser_name(),
                u.getUser_passwd(),
                u.getUser_sex(),
                u.getUser_birthday(),
                u.getUser_idenity_code(),
                u.getUser_email(),
                u.getUser_mobile(),
                u.getUser_address(),
                u.getUser_status()
        };

        int count = BaseDao.exectuIUD(sql, params);
        return count;
    }

    public static int del(String id) {

        String sql = "delete from lmonkey_user where user_id=? and user_status!=2";
        Object[] parmas = {id};

         return BaseDao.exectuIUD(sql, parmas);

    }



    public static int update(LMONKEY_USER u) {
//        String sql = "update  LMONKEY_USER set user_name=?,user_passwd=?,user_sex=?,user_birthday=DATE_FORMAT(?,'%y-%m-%d-), user_idenity_code=?,user_email=?,user_mobile=?,user_address=?,user_status=? where user_id=?";


        String sql = "update lmonkey_user set user_name=?, user_passwd=?, user_sex=?, user_birthday=DATE_FORMAT(?, '%y-%m-%d'),user_idenity_code=?, user_email=?, user_mobile=?, user_address=?,user_status=? where user_id=?";

        Object[] params = {
                u.getUser_name(),
                u.getUser_passwd(),
                u.getUser_sex(),
                u.getUser_birthday(),
                u.getUser_idenity_code(),
                u.getUser_email(),
                u.getUser_mobile(),
                u.getUser_address(),
                u.getUser_status(),
                u.getUser_id()
        };

        return  BaseDao.exectuIUD(sql, params);

    }


    //获取总记录数和总页数
    public static int selectByName(String id) {
        int count = 0;
        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "";
            sql = "select count(*) from lmonkey_user where user_id=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                count = rs.getInt(1);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            BaseDao.closeall(rs, ps, conn);
        }
        return count;
    }





    //获取总记录数和总页数
    public static int[] totalPage(int count,String keyWords) {
        int[] arr = {0, 1};


        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "";

            if (keyWords != null) {
                sql = "select count(*) from lmonkey_user where user_name like ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1,"%"+keyWords+"%");
            } else {
                sql = "select count(*) from lmonkey_user";
                ps = conn.prepareStatement(sql);
            }
            rs = ps.executeQuery();

            while (rs.next()) {
                arr[0] = rs.getInt(1);

                if (arr[0] % count == 0) {
                    arr[1] = arr[0] / count;

                } else {
                    arr[1] = arr[0] / count + 1;
                }


            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            BaseDao.closeall(rs, ps, conn);
        }
        return arr;
    }


    public static ArrayList<LMONKEY_USER> selectAll(int cpage, int count,String keyWord) {

        ArrayList<LMONKEY_USER> list = new ArrayList<>();


        //声明结果集
        ResultSet rs = null;
        //获取连接对象
        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;



        try {
            String sql = "";
            if (keyWord != null) {
                sql = "select * from LMONKEY_USER where user_name like ? order by USER_BIRTHDAY desc limit ?, ? ";
                ps = conn.prepareStatement(sql);
                ps.setString(1,"%"+keyWord+"%");
                ps.setInt(2, (cpage - 1) * count);

                ps.setInt(3,count);
            } else {
                sql = "select * from LMONKEY_USER order by USER_BIRTHDAY desc limit ?, ? ";
                ps = conn.prepareStatement(sql);

                ps.setInt(1, (cpage - 1) * count);

                ps.setInt(2,count);
            }



            rs = ps.executeQuery();
            while (rs.next()) {

                LMONKEY_USER u = new LMONKEY_USER(
                        rs.getString("USER_ID"),
                        rs.getString("USER_NAME"),
                        rs.getString("USER_PASSWD"),
                        rs.getString("USER_SEX"),
                        rs.getString("USER_BIRTHDAY"),
                        rs.getString("USER_IDENITY_CODE"),
                        rs.getString("USER_EMAIL"),
                        rs.getString("USER_MOBILE"),
                        rs.getString("USER_ADDRESS"),
                        rs.getInt("USER_STATUS")
                );

                list.add(u);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            BaseDao.closeall(rs, ps, conn);
        }

        return list;
    }
//通过id查找用户
    public static LMONKEY_USER selectById(String id) {

        LMONKEY_USER u = null;


        //声明结果集
        ResultSet rs = null;
        //获取连接对象
        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;



        try {
            String sql = "select m.*, DATE_FORMAT(m.user_birthday,'%Y-%m-%d')birthday from LMONKEY_USER m where user_id=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);




            rs = ps.executeQuery();
            while (rs.next()) {

                 u = new LMONKEY_USER(
                        rs.getString("USER_ID"),
                        rs.getString("USER_NAME"),
                        rs.getString("USER_PASSWD"),
                        rs.getString("USER_SEX"),
                        rs.getString("birthday"),
                        rs.getString("USER_IDENITY_CODE"),
                        rs.getString("USER_EMAIL"),
                        rs.getString("USER_MOBILE"),
                        rs.getString("USER_ADDRESS"),
                        rs.getInt("USER_STATUS")
                );


            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            BaseDao.closeall(rs, ps, conn);
        }

        return u;
    }

    public static int selectByNM(String name, String pwd) {
        int count = 0;

        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "";
            sql = "select count(*) from lmonkey_user where user_id=? and user_passwd=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, pwd);
            rs = ps.executeQuery();

            while (rs.next()) {
                count = rs.getInt(1);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            BaseDao.closeall(rs, ps, conn);
        }


        return count;
    }


    /*通过用户名和密码查询用户信息*/
    public static LMONKEY_USER selectAdmin(String name, String pwd) {
        LMONKEY_USER u = null;


        //声明结果集
        ResultSet rs = null;
        //获取连接对象
        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;



        try {
            String sql = "select m.*, DATE_FORMAT(m.user_birthday,'%Y-%m-%d')birthday from LMONKEY_USER m where user_id=? and user_passwd=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, pwd);
            rs = ps.executeQuery();
            while (rs.next()) {

                u = new LMONKEY_USER(
                        rs.getString("USER_ID"),
                        rs.getString("USER_NAME"),
                        rs.getString("USER_PASSWD"),
                        rs.getString("USER_SEX"),
                        rs.getString("birthday"),
                        rs.getString("USER_IDENITY_CODE"),
                        rs.getString("USER_EMAIL"),
                        rs.getString("USER_MOBILE"),
                        rs.getString("USER_ADDRESS"),
                        rs.getInt("USER_STATUS")
                );


            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            BaseDao.closeall(rs, ps, conn);
        }

        return u;
    }


}
