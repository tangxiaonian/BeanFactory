package com.tang.Utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author ASUS
 * @create 2018-12-25 20:16
 */
public class DbUtils {

    private static ComboPooledDataSource dataSource;
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();

    static {

        dataSource = new ComboPooledDataSource();

        InputStream inputStream = DbUtils.class.getClassLoader().getResourceAsStream("config.properties");

        Properties properties = new Properties();

        String username = null;
        String password = null;
        String driverClass = null;
        String url = null;

        try {

            properties.load(inputStream);

            username = properties.getProperty("username");
            password = properties.getProperty("password");
            driverClass = properties.getProperty("driverClass");
            url = properties.getProperty("url");

        } catch (IOException e) {

            System.out.println("没有找到classpath下的config.properties配置文件");

            e.printStackTrace();

        }

        try {
            dataSource.setDriverClass(driverClass);
            dataSource.setPassword(password);
            dataSource.setUser(username);
            dataSource.setJdbcUrl(url);
        } catch (PropertyVetoException e) {
            System.out.println("数据库连接失败....");
            e.printStackTrace();
        }

    }

    public static DatabaseMetaData getDatabaseMetaData(){

        DbUtils.getConnection();
        Connection connection = threadLocal.get();
        try {
            return connection.getMetaData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static Connection getConnection(){

        Connection connection = threadLocal.get();

        if (connection == null){
            try {
                threadLocal.set(dataSource.getConnection());
            } catch (SQLException e) {
                System.out.println("数据库连接失败....");
                e.printStackTrace();
            }
        }
        return connection;

    }

    public static void close(Connection connection){

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
