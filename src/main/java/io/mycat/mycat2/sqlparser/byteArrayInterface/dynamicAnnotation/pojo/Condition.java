package io.mycat.mycat2.sqlparser.byteArrayInterface.dynamicAnnotation.pojo;

/**
 * Created by jamie on 2017/9/10.
 */
public class Condition {
    Operation operation;
    String expression;

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }
}
