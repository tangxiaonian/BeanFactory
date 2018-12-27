package com.tang.Utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ASUS
 * @create 2018-12-26 15:00
 */
public class TypeUtils {

    public static Map<Integer,String> typeMap = new HashMap<Integer, String>();

    static {

        typeMap.put(1,"Character[]");
        typeMap.put(4,"Integer");
        typeMap.put(6,"Float");
        typeMap.put(8,"Double");
        typeMap.put(12,"String");
        typeMap.put(16,"Boolean");
        typeMap.put(91,"java.util.Date");

    }

    public static String sqlTypeToJavaType(Integer key){

        String value = typeMap.get(key);

        if (null == value)
            value = "String";

        return value;
    }

}
