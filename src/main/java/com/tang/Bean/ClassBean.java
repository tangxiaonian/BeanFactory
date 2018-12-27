package com.tang.Bean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ASUS
 * @create 2018-12-27 14:23
 */
public class ClassBean {

    private String topBody;
    private String className;
    private String fieldName;
    private String fieldType;
    private String getMethod;
    private String setMethod;
    private String bottomBody;

    public String getTopBody() {
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        this.topBody = "package com.tang.Bean;\n" +
                "\n" +
                "/**\n" +
                " * @author Tang\n" +
                " * @create "+format+"\n" +
                " */\n" +
                "public class "+this.getClassName()+" {\n\n";

        return topBody;
    }

    private String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    private void setTopBody(String topBody) {
        this.topBody = "";
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {

        String first = className.substring(0,1).toUpperCase();
        String word = className.substring(1);

        this.className = first + word;
    }

    public String getFieldName() {
        return "\tprivate "+this.getFieldType()+" "+this.fieldName+";\n\n";
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getGetMethod() {
//        private String name ;
//        setName(String name){
//            this.name = name;
//        }
        String first = this.fieldName.substring(0,1).toUpperCase();
        String word =this.fieldName.substring(1,this.fieldName.length());

        return "\tpublic "+this.getFieldType()+" get"+first+word+"("+this.getFieldType()+" "+this.fieldName+") {\n" +
                "        return this."+this.fieldName+";\n" +
                "    }\n\n";
    }

    public void setGetMethod(String getMethod) {
        this.getMethod = getMethod;
    }

    public String getSetMethod() {

        String first = this.fieldName.substring(0,1).toUpperCase();
        String word =this.fieldName.substring(1,this.fieldName.length());

        return "\tpublic void set"+first+word+"("+this.getFieldType()+" "+this.fieldName+") {\n" +
                "        this."+this.fieldName+" = "+this.fieldName+";\n" +
                "    }\n\n";
    }

    public void setSetMethod(String setMethod) {
        this.setMethod = setMethod;
    }

    public String getBottomBody() {
        return "}";
    }

    public void setBottomBody(String bottomBody) {
        this.bottomBody = bottomBody;
    }

    public String getClassFile(ClassBean classBean){

        return classBean.getTopBody() + classBean.getFieldName()
        + classBean.getGetMethod()
        + classBean.getSetMethod()
        + classBean.getBottomBody();
    }
}
