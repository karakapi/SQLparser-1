package io.mycat.mycat2.sqlparser.byteArrayInterface.dynamicAnnotation.pojo;

/**
 * Created by jamie on 2017/9/10.
 */
public enum Operation {
    AND("AND"),OR("OR");
    private final String text;
    private Operation(final String text){
        this.text=text;
    }
}
