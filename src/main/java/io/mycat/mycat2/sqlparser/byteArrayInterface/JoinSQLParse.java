package io.mycat.mycat2.sqlparser.byteArrayInterface;

import io.mycat.mycat2.sqlparser.BufferSQLContext;
import io.mycat.mycat2.sqlparser.SQLParseUtils.HashArray;
import io.mycat.mycat2.sqlparser.TokenHash;
import io.mycat.mycat2.sqlparser.byteArrayInterface.expr.ExprSQLParser;

/**
 * Created by jamie on 2017/9/9.
 */
public class JoinSQLParse {

    /*
    table_references:
    escaped_table_reference [, escaped_table_reference] ...
*/
    public static int pickTableReferences(int pos, final int arrayCount, BufferSQLContext context, HashArray hashArray, ByteArrayInterface sql) {
        pos = pickEscapedTableReference(pos, arrayCount, context, hashArray, sql);
        int type = hashArray.getType(pos);
        while (type == Tokenizer2.COMMA) {
            ++pos;
            pos = pickEscapedTableReference(pos, arrayCount, context, hashArray, sql);
            type = hashArray.getType(pos);
        }
        return pos;
    }

    /*
escaped_table_reference:
table_reference
| { OJ table_reference }
*/
    public static int pickEscapedTableReference(int pos, final int arrayCount, BufferSQLContext context, HashArray hashArray, ByteArrayInterface sql) {
        int type = hashArray.getType(pos);
        if (type == Tokenizer2.LEFT_CURLY_BRACKET) {
            ++pos;
            pos = pickTableReference(pos, arrayCount, context, hashArray, sql);
            type = hashArray.getType(pos);
            if (type == Tokenizer2.RIGHT_CURLY_BRACKET) {
                ++pos;
                return pos;
            }
            //语法错误
        }
        return pickEscapedTableReference(pos, arrayCount, context, hashArray, sql);
    }

    /*
table_reference:
table_factor
| join_table
*/
    public static int pickTableReference(int pos, final int arrayCount, BufferSQLContext context, HashArray hashArray, ByteArrayInterface sql) {
        pos = pickTableFactor(pos, arrayCount, context, hashArray, sql);
        pos = pickJoinTable(pos, arrayCount, context, hashArray, sql);
        return pos;
    }

    /*
table_factor:
tbl_name [PARTITION (partition_names)]
[[AS] alias] [index_hint_list]
| table_subquery [AS] alias
| ( table_references )
*/
    public static int pickTableFactor(int pos, final int arrayCount, BufferSQLContext context, HashArray hashArray, ByteArrayInterface sql) {
        TokenizerUtil.debug(pos, context);
        ++pos;
        long longHash = hashArray.getHash(pos);
        int type = hashArray.getType(pos);
        if (Tokenizer2.LEFT_PARENTHESES == type) {
            longHash = hashArray.getHash(++pos);
            if (TokenHash.SELECT == longHash) {
                pos = pickTableSubquery(pos, arrayCount, context, hashArray, sql);
            } else {
                pos = pickTableReferences(pos, arrayCount, context, hashArray, sql);
            }
            type = hashArray.getType(++pos);
            if (Tokenizer2.RIGHT_PARENTHESES == type) {
                ++pos;
                return pos;
            }
        }
        if (TokenHash.PARTITION == longHash) {
            type = hashArray.getType(++pos);
            if (Tokenizer2.LEFT_PARENTHESES == type) {
                ++pos;
                //todo partition_names
                type = hashArray.getType(++pos);
                if (Tokenizer2.RIGHT_PARENTHESES == type) {
                    ++pos;
                    return pos;
                }
            }
        }
        if (TokenHash.AS == longHash) {
            ++pos;
            longHash = hashArray.getHash(pos);
            //todo 捕获  alias
            ++pos;
        } else if (TokenHash.USE != longHash && TokenHash.IGNORE != longHash && TokenHash.FORCE != longHash) {
            ++pos;
            longHash = hashArray.getHash(pos);
            //todo 捕获  alias
            ++pos;
        }
        //todo [index_hint_list]
        return pos;
    }

    /*
join_table:
table_reference [INNER | CROSS] JOIN table_factor [join_condition]
| table_reference STRAIGHT_JOIN table_factor
| table_reference STRAIGHT_JOIN table_factor ON conditional_expr
| table_reference {LEFT|RIGHT} [OUTER] JOIN table_reference join_condition
| table_reference NATURAL [{LEFT|RIGHT} [OUTER]] JOIN table_factor
*/
    public static int pickJoinTable(int pos, final int arrayCount, BufferSQLContext context, HashArray hashArray, ByteArrayInterface sql) {
        long longHash = hashArray.getHash(pos);
        if (TokenHash.STRAIGHT_JOIN == longHash) {
            ++pos;
            pos = pickTableFactor(pos, arrayCount, context, hashArray, sql);
            longHash = hashArray.getHash(pos);
            if (TokenHash.ON == longHash) {
                ++pos;
                longHash = hashArray.getHash(pos);
                if (TokenHash.WHERE == longHash) {
                    ++pos;
                    return ExprSQLParser.pickExpr(pos, arrayCount, context, hashArray, sql);
                }
            } else {
                return pos;
            }
        } else if (TokenHash.NATURAL == longHash) {
            longHash = hashArray.getHash(++pos);
            if (TokenHash.LEFT == longHash || TokenHash.RIGHT == longHash) {
                ++pos;
                longHash = hashArray.getHash(pos);
                if (TokenHash.OUTER == longHash) {
                    ++pos;
                    longHash = hashArray.getHash(pos);
                    if (TokenHash.JOIN == longHash) {
                        ++pos;
                        return pickTableFactor(pos, arrayCount, context, hashArray, sql);
                    }
                }
            }
        } else if (TokenHash.INNER == longHash || TokenHash.CROSS == longHash) {
            ++pos;
            longHash = hashArray.getHash(pos);
            //[INNER | CROSS] JOIN table_factor [join_condition]
            if (TokenHash.JOIN == longHash) {
                ++pos;
                pos = pickTableFactor(pos, arrayCount, context, hashArray, sql);
                return pickJoinCondition(pos, arrayCount, context, hashArray, sql);
            }
        } else if (TokenHash.LEFT == longHash || TokenHash.RIGHT == longHash) {
            //  {LEFT|RIGHT} [OUTER] JOIN table_reference join_condition
            ++pos;
            longHash = hashArray.getHash(pos);
            if (TokenHash.OUTER == longHash) {
                ++pos;
                longHash = hashArray.getHash(pos);
                if (TokenHash.JOIN == longHash) {
                    ++pos;
                    pos = pickTableReference(pos, arrayCount, context, hashArray, sql);
                    return pickJoinCondition(pos, arrayCount, context, hashArray, sql);/**/
                }
            }
        }
        return pos;
    }

    public static int pickTableSubquery(int pos, final int arrayCount, BufferSQLContext context, HashArray hashArray, ByteArrayInterface sql) {
        long longHash = hashArray.getHash(pos);
        return pos;
    }

    public static int pickJoinCondition(int pos, final int arrayCount, BufferSQLContext context, HashArray hashArray, ByteArrayInterface sql) {
        long longHash = hashArray.getHash(pos);
        if (TokenHash.OUT == longHash) {
            ++pos;
            longHash = hashArray.getHash(pos);
            if (TokenHash.WHERE == longHash) {
                ++pos;
                return ExprSQLParser.pickExpr(pos, arrayCount, context, hashArray, sql);
            }
        } else if (TokenHash.USING == longHash) {
//todo USING (column_list)
        }
        return pos;
    }

}
