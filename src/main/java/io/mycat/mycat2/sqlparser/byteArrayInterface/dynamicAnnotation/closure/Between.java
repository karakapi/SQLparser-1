package io.mycat.mycat2.sqlparser.byteArrayInterface.dynamicAnnotation.closure;

import io.mycat.mycat2.sqlparser.BufferSQLContext;
import io.mycat.mycat2.sqlparser.SQLParseUtils.HashArray;
import io.mycat.mycat2.sqlparser.TokenHash;
import io.mycat.mycat2.sqlparser.byteArrayInterface.ByteArrayInterface;

/**
 * Created by jamie on 2017/9/10.
 */
public class Between {
    final long arg1;
    final long arg2;

    public int matchBetweenRest(int pos, final int arrayCount, final BufferSQLContext context, final HashArray hashArray, final ByteArrayInterface sql) {
        int start=pos;
        if(arg1 == hashArray.getHash(pos++) && TokenHash.AND == hashArray.getHash(pos + 1) && arg2 == hashArray.getHash(pos++)){
            return pos;
        }else {
            return start;
        }
    }

    public Between(long arg1, long arg2) {
        this.arg1 = arg1;
        this.arg2 = arg2;
    }
}
