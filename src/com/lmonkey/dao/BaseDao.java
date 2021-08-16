package com.lmonkey.dao;

import java.sql.*;

/**
 * @Auther huijia
 * @Date 2021/7/17
 **/
public class BaseDao {

    static {
        //加载驱动
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public static Connection getconn() {
        //创建一个连接对象
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lmonkeyshop?useSSL=false&serverTimezone=UTC","root","root");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return conn;
    }

    public static int  exectuIUD(String sql, Object[] params) {
        int count = 0;
        Connection conn = BaseDao.getconn();

        //准备sql语句进行执行
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(sql);

            for(int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }

            count = ps.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            BaseDao.closeall(null, ps, conn);
        }
        return count;
    }

    public static void closeall(ResultSet rs, PreparedStatement ps, Connection conn) {

        try {
            if(ps != null) {
                ps.close();
            }
            if(conn != null)
                conn.close();
            if(rs != null)
                rs.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
