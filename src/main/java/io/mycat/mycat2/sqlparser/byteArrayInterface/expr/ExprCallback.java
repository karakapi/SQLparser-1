package io.mycat.mycat2.sqlparser.byteArrayInterface.expr;

/**
 * Created by jamie on 2017/9/9.
 */
public interface ExprCallback<T> {
//    expr:
//    expr OR expr
//  | expr || expr
//  | expr XOR expr
//  | expr AND expr
//  | expr && expr
//  | NOT expr
//  | ! expr
//  | boolean_primary IS [NOT] {TRUE | FALSE | UNKNOWN}
//  | boolean_primary
    default boolean orExpr(T left,T right){
        return false;
    }
    default boolean andExpr(T left,T right){
        return false;
    }
    default boolean xorExpr(T left,T right){
        return false;
    }
    default boolean notExpr(T left){
        return false;
    }
    default boolean colonExpr(T left){
        return false;
    }
    default boolean isExpr(T left,boolean not,int type){
        return false;
    }
    /**
     boolean_primary:
     boolean_primary IS [NOT] NULL
     | boolean_primary <=> predicate
     | boolean_primary comparison_operator predicate
     | boolean_primary comparison_operator {ALL | ANY} (subquery)
     | predicate
     */
    default boolean booleanPrimaryIsExpr(T left,boolean not,int type){
        return false;
    }
}
