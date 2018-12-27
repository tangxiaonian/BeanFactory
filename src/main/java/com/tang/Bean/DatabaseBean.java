package com.tang.Bean;

import java.util.Map;

/**
 * @author ASUS
 * @create 2018-12-25 20:49
 */
public class DatabaseBean {

    private java.lang.String dbName;
    private Map<String, Table> tables;

    public DatabaseBean(){}

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public Map<String, Table> getTables() {
        return tables;
    }

    public void setTables(Map<String, Table> tables) {
        this.tables = tables;
    }

    @Override
    public String toString() {
        return "DatabaseBean{" +
                "dbName='" + dbName + '\'' +
                ", tables=" + tables +
                '}';
    }
}
