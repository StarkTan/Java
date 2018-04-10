package com.stark.jdbc.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Strak on 2017/4/21.
 * JDBC 工具类，用于获取链接
 */
public class JDBCUtils
{
    public static Connection getConn() {
        //String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/dbtest?serverTimezone=UTC"; //serverTimezone=UTC 消除少8个小时的问题
        String username = "root";
        String password = "root";
        Connection conn = null;
        try {
            //Class.forName(driver); //classLoader,加载对应驱动
            //DriverManager类用于管理驱动，建立程序和数据库的链接,包含getConnection()，setLoginTime(),println
            conn =  DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("创建");
        return conn;
    }
}
