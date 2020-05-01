package com.liangweimin.www.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author 梁伟民
 */
public class JDBCUtil {

//    /**
//     * 获取连接
//     *
//     * @return
//     * @throws ClassNotFoundException
//     * @throws SQLException
//     */
//    public static Connection getConnection() throws ClassNotFoundException, SQLException {
//        String url = "jdbc:mysql://localhost:3306/appointments?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=UTF8&useSSL=false";
//        String user = "root";
//        String password = "369258258Aa@";
//
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        Connection conn = DriverManager.getConnection(url, user, password);
//
//        return conn;
//    }
//
//    /**
//     * 关闭资源
//     *
//     * @param conn
//     * @param ps
//     * @param rs
//     */
//    public static void closeSource(Connection conn, Statement ps, ResultSet rs) {
//        try {
//            if (conn != null) {
//                conn.close();
//            }
//            if (ps != null) {
//                ps.close();
//            }
//            if (rs != null) {
//                rs.close();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 关闭资源
//     *
//     * @param conn
//     * @param ps
//     */
//    public static void closeSource(Connection conn, Statement ps) {
//        try {
//            if (conn != null) {
//                conn.close();
//            }
//            if (ps != null) {
//                ps.close();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }


    /**
     * 定义成员变量
     */
    private static DataSource ds;

    static {
        try {
            //1.加载配置文件
            Properties pro = new Properties();
            InputStream is = JDBCUtil.class.getClassLoader().getResourceAsStream("druid.properties");
            if (is != null) {
                pro.load(is);
            }

            //2.获取DataSource
            ds = DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取连接
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    /**
     * 关闭资源
     * @param stmt
     * @param conn
     */
    public static void closeSource(Connection conn, Statement stmt){
        closeSource(conn,stmt,null);
    }

    /**
     * 关闭资源
     * @param stmt
     * @param conn
     * @param rs
     */
    public static void closeSource(Connection conn, Statement stmt, ResultSet rs){
        if (stmt != null) {
            try {
                //关闭资源
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                //归还连接
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 获取连接池
     * @return
     */
    public static DataSource getDataSource(){
        return ds;
    }
}
