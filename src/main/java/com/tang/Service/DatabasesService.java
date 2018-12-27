package com.tang.Service;

import com.tang.Bean.DatabaseBean;
import com.tang.Bean.Table;
import com.tang.Bean.TableStruct;
import com.tang.Dao.DatabasesDao;
import com.tang.Utils.DbUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ASUS
 * @create 2018-12-25 21:14
 */
public class DatabasesService {

    private DatabasesDao databasesDao = new DatabasesDao();

    public DatabaseBean getDbInfo(){
        try {
            return this.getTables();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

//    获取数据库下面的表信息
    private DatabaseBean getTables() throws SQLException {
//        封装数据库的信息
        DatabaseBean databaseBean = new DatabaseBean();
//        封装表的名字集合
        List<String> tables = new ArrayList<String>();

        ResultSet tablesResult = databasesDao.getTables();

        while (tablesResult.next()){
//            dbName
            databaseBean.setDbName(tablesResult.getString("TABLE_CAT"));
//            tableName
            tables.add(tablesResult.getString("TABLE_NAME"));
        }

//      获取 表名 : 表
        Map<String, Table> tableMap = this.getColumns(tables);

        databaseBean.setTables(tableMap);

        return databaseBean;
    }

    //    获取数据库下的每个表的列信息
    public Map<String,Table> getColumns( List<String> tables) throws SQLException {

        Map<String,Table> tableMap = new HashMap<String, Table>();

        for (String tableName : tables){

            //        拿到指定表的列信息集合
            ResultSet tablesResult = databasesDao.getColumns(tableName);

//                表
            Table table = new Table();
//            表结构
            List<TableStruct> tableStructList = new ArrayList<TableStruct>();
            while (tablesResult.next()){
//                表结构
                TableStruct tableStruct = new TableStruct();
//                表info
                table.setTable_Name(tableName);
//                设置表结构info
                tableStruct.setColumn_Name(tablesResult.getString("COLUMN_NAME"));
                tableStruct.setColumn_Type(tablesResult.getString("DATA_TYPE"));
                tableStruct.setColumn_Size(tablesResult.getInt("COLUMN_SIZE"));

                tableStructList.add(tableStruct);
            }
//            设置表的表结构信息
            table.setTableStruct(tableStructList);
            tableMap.put(tableName,table);
        }

        //        关闭数据库连接
        try {
            databasesDao.close(DbUtils.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tableMap;
    }

}
