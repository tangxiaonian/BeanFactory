package com.tang.Bean;

import java.util.List;

/**
 * @author ASUS
 * @create 2018-12-25 20:49
 */
public class Table {

    private java.lang.String Table_Name;
    //    表结构
    private List<TableStruct> tableStruct;

    public Table(){}

    public List<TableStruct> getTableStruct() {
        return tableStruct;
    }

    public void setTableStruct(List<TableStruct> tableStruct) {
        this.tableStruct = tableStruct;
    }

    public java.lang.String getTable_Name() {
        return Table_Name;
    }

    public void setTable_Name(java.lang.String table_Name) {
        Table_Name = table_Name;
    }

    @Override
    public String toString() {
        return "Table{" +
                "Table_Name='" + Table_Name + '\'' +
                ", tableStruct=" + tableStruct +
                '}';
    }
}
