package com.tang.Service;

import com.tang.Bean.ClassBean;
import com.tang.Bean.DatabaseBean;
import com.tang.Bean.Table;
import com.tang.Bean.TableStruct;
import com.tang.Utils.FileUtils;
import com.tang.Utils.TypeUtils;
import com.tang.com.tang.NotFoundFileException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ASUS
 * @create 2018-12-26 16:34
 */
public class CreateFileService {

    private String fileSavePath = FileUtils.getFilePath();
    private DatabasesService databasesService = new DatabasesService();


    public void start(){

        try {
            this.createFile();
        } catch (NotFoundFileException e) {
            e.printStackTrace();
        }
    }


    private void createFile() throws NotFoundFileException {

        File file = new File(fileSavePath);

        if (file.exists()) {

            Map<String, String> table = this.getTable();

            System.out.println("文件生成目录为:"+fileSavePath+"\n");
//          开始创建文件
            table.forEach((k, v) -> {
//              创建每一个java文件
                File newFile = new File(fileSavePath, k + ".java");

                System.out.println("开始生成"+k + ".java文件....");

                if (!newFile.exists()) {

                    boolean flage = false;

                    try {
//                        创建文件
                        flage = newFile.createNewFile();
//                    写入内容
                        if (flage) {
//                          将类内容写入文件
                            PrintWriter printWriter = new PrintWriter(newFile);

                            printWriter.print(v);

                            printWriter.flush();

                            printWriter.close();

                            System.out.println(k+".java文件生成成功!\n");

                        }
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else {
                    System.out.println("文件已存在,无法重复生成!请删除...\n");
                }
            });
        } else {
            throw new NotFoundFileException("文件路径不存在");
        }

    }
//              k:表名 V:文件内容
    private Map<String, String> getTable() {
//      存放生成的类名  和   类的内容
        Map<String, String> tables = new HashMap<String, String>();
//      拿到整个数据库的信息
        DatabaseBean dbInfo = databasesService.getDbInfo();
//      获取数据库下的表
        Map<String, Table> tableMap = dbInfo.getTables();

        for (String tableName : tableMap.keySet()) {
//          创建保存生成文件的info对象
            ClassBean classBean = new ClassBean();

            classBean.setClassName(tableName);
//          根据表名取出表对象
            Table table = tableMap.get(tableName);
//          获取表结构
            List<TableStruct> tableStructList = table.getTableStruct();
//          完整表信息的StringBuffer
            StringBuffer tableBuffer = new StringBuffer();
            StringBuffer setOrGetMethod = new StringBuffer();
//            追加类头部
            tableBuffer.append(classBean.getTopBody());

//            追加字段名 get/set方法
            for (TableStruct tableStruct : tableStructList) {

                classBean.setFieldName(tableStruct.getColumn_Name());
//                sql类型转java类型
                classBean.setFieldType(TypeUtils.sqlTypeToJavaType(new Integer(tableStruct.getColumn_Type())));

                tableBuffer.append(classBean.getFieldName());
//              添加get/set方法
                setOrGetMethod.append(classBean.getGetMethod());
                setOrGetMethod.append(classBean.getSetMethod());
            }

            tableBuffer.append(setOrGetMethod);

//          类尾部
            tableBuffer.append(classBean.getBottomBody());

            tables.put(classBean.getClassName(), tableBuffer.toString());
        }

        return tables;
    }


}
