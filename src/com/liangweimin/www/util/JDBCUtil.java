package com.liangweimin.www.util;

import java.sql.*;

/**
 * @author 梁伟民
 */
public class JDBCUtil {

    /**
     * 获取连接
     *
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost:3306/appointments?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=UTF8&useSSL=false";
        String user = "root";
        String password = "369258258Aa@";

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(url, user, password);

        return conn;
    }

    /**
     * 关闭资源
     *
     * @param conn
     * @param ps
     * @param rs
     */
    public static void closeSource(Connection conn, Statement ps, ResultSet rs) {
        try {
            if (conn != null) {
                conn.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭资源
     *
     * @param conn
     * @param ps
     */
    public static void closeSource(Connection conn, Statement ps) {
        try {
            if (conn != null) {
                conn.close();
            }
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
