package com.tang.Bean;

/**
 * @author ASUS
 * @create 2018-12-25 20:53
 */
/*
    表结构
 */
public class TableStruct {

    private String Column_Name;
//    列大小
    private Integer Column_Size;
    private String Column_Type;

    public TableStruct(String column_Name, Integer column_Size, String column_Type) {
        Column_Name = column_Name;
        Column_Size = column_Size;
        Column_Type = column_Type;
    }

    public TableStruct() {

    }

    public String getColumn_Name() {
        return Column_Name;
    }

    public void setColumn_Name(String column_Name) {
        Column_Name = column_Name;
    }

    public Integer getColumn_Size() {
        return Column_Size;
    }

    public void setColumn_Size(Integer column_Size) {
        Column_Size = column_Size;
    }

    public String getColumn_Type() {
        return Column_Type;
    }

    public void setColumn_Type(String column_Type) {
        Column_Type = column_Type;
    }


    @Override
    public String toString() {
        return "TableStruct{" +
                "Column_Name='" + Column_Name + '\'' +
                ", Column_Size=" + Column_Size +
                ", Column_Type='" + Column_Type + '\'' +
                '}';
    }
}
