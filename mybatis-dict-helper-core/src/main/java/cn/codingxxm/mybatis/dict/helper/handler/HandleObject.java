package cn.codingxxm.mybatis.dict.helper.handler;

import org.apache.ibatis.reflection.MetaObject;

public class HandleObject {

    private String property;

    private String column;

    private String text;

    private String table;

    private String conditions;

    private MetaObject metaObject;

    private Object fieldValue;

    public void setProperty(String property) {
        this.property = property;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public void setMetaObject(MetaObject metaObject) {
        this.metaObject = metaObject;
    }

    public String getProperty() {
        return property;
    }

    public String getColumn() {
        return column;
    }

    public String getTable() {
        return table;
    }

    public String getConditions() {
        return conditions;
    }

    public MetaObject getMetaObject() {
        return metaObject;
    }

    public Object getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(Object fieldValue) {
        this.fieldValue = fieldValue;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "HandleObject{" +
                "property='" + property + '\'' +
                ", column='" + column + '\'' +
                ", text='" + text + '\'' +
                ", table='" + table + '\'' +
                ", conditions='" + conditions + '\'' +
                ", fieldValue=" + fieldValue +
                '}';
    }
}
