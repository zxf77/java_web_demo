package com.zxf.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.*;

/**
 * 链接数据库的工具类,使用c3p0数据库连接池
 */
public class JDBCTools {
    private static DataSource dataSource;
    //链接数据库驱动只执行一次，放到静态代码块里面
    static {
        dataSource = new ComboPooledDataSource("testc3p0");
    }
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    //释放资源
    public static void release(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            if (connection != null) {
                connection.close();
            } if (statement != null) {
                statement.close();
            } if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
