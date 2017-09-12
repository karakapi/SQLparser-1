package io.mycat.mycat2.sqlparser.byteArrayInterface.dynamicAnnotation.closure;

import io.mycat.mycat2.sqlparser.BufferSQLContext;
import io.mycat.mycat2.sqlparser.SQLParseUtils.HashArray;
import io.mycat.mycat2.sqlparser.byteArrayInterface.ByteArrayInterface;
import io.mycat.mycat2.sqlparser.byteArrayInterface.Tokenizer2;

/**
 * Created by jamie on 2017/9/10.
 */
public class NotIn {
    final long value;
    final long[] args;

    public int matchInRest(int pos, final int arrayCount, final BufferSQLContext context, final HashArray hashArray, final ByteArrayInterface sql) {
        int start=pos;
        int type = hashArray.getType(pos);
        if (Tokenizer2.LEFT_PARENTHESES == type) {
            ++pos;
            int size=args.length;
            for (int i = 0; i <size&&pos<arrayCount ; i++) {
                if(args[i]== hashArray.getHash(pos++)){
                    type = hashArray.getType(pos);
                    if (type==Tokenizer2.COMMA){
                        ++pos;
                        continue;
                    }else {
                        break;
                    }
                }
                return start;
            }
            type = hashArray.getType(pos);
            if (Tokenizer2.RIGHT_PARENTHESES == type) {
                ++pos;
                return pos;
            }
        }
        return start;
    }

    public NotIn(long value, long[] args) {
        this.value = value;
        this.args = args;
    }
}
