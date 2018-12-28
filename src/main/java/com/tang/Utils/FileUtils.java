package com.tang.Utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.tang.com.tang.NotFoundFileException;

import java.beans.PropertyVetoException;
import java.io.File;
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
public class FileUtils {

    public static String fileSavePath = null;
    private static final String joinPath = "src\\main\\java\\com\\tang\\ResultBean";
    private static String packageName = "com.tang.ResultBean";

    static {

        InputStream inputStream = FileUtils.class.getClassLoader().getResourceAsStream("config.properties");

        Properties properties = new Properties();

        try {

            properties.load(inputStream);

            fileSavePath = properties.getProperty("fileSavePath");
            packageName = properties.getProperty("packageName");

            if (packageName == null){
                packageName = "com.tang.ResultBean";
            }else {
                if (packageName.trim().length() == 0){
                    packageName = "com.tang.ResultBean";
                }
            }

//            没有指定目录 就指定默认保存目录
            if (fileSavePath != null && fileSavePath.trim().length() != 0){

                File file = new File(fileSavePath);

                boolean exists = file.exists();

                if (!exists){
//                    用户指定的目录不存在  异常抛出
                    throw new NotFoundFileException("没有发现此目录的存在,请检查!");
                }else{
                    fileSavePath = file.getAbsolutePath();
                    System.out.println("文件生成目录为:"+fileSavePath);
                }

            }else{
                String path = new File(".").getAbsolutePath();
                String newPath = path.substring(0,path.length() - 1) + joinPath;
                fileSavePath = newPath;
                System.out.println("默认文件生成路径为当前项目下的ResultBean包下....");
            }

        } catch (IOException e) {

            System.out.println("没有找到classpath下的config.properties配置文件");

            e.printStackTrace();

        } catch (NotFoundFileException e) {
            e.printStackTrace();
        }

    }

    public static String getPackageName() {
        return packageName;
    }

    public static String getFilePath(){
        return fileSavePath;
    }

}
