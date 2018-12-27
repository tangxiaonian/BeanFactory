package com.tang.Dao;

import com.tang.Utils.DbUtils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ASUS
 * @create 2018-12-25 21:06
 */
public class DatabasesDao {

    private DatabaseMetaData databaseMetaData = DbUtils.getDatabaseMetaData();

//    返回指定数据库里的所有的表
    public ResultSet getTables(){

        ResultSet tables = null;

        try {
//                                        类别   指定的数据库   匹配的模式   类型
            tables = databaseMetaData.getTables("", "drivermanager",
                    "%", new String[]{"TABLE"});

        } catch (SQLException e) {

            System.out.println("获取数据库元信息失败!");

            e.printStackTrace();
        }
        return tables;

    }

//    返回指定表中的所有列的数目
    public ResultSet getColumns(String tableName){

        ResultSet columns = null;

        try {
            columns = databaseMetaData.getColumns("", "drivermanager",
                    tableName, "%");
        } catch (SQLException e) {

            System.out.println("列信息获取失败!");

            e.printStackTrace();
        }

        return columns;
    }

    public void close(Connection connection) throws SQLException {
        DbUtils.close(connection);
    }
}
