package io.mycat.mycat2.sqlparser.byteArrayInterface.dynamicAnnotation;

import io.mycat.mycat2.sqlparser.BufferSQLContext;
import io.mycat.mycat2.sqlparser.SQLParseUtils.HashArray;
import io.mycat.mycat2.sqlparser.byteArrayInterface.ByteArrayInterface;

/**
 * Created by jamie on 2017/9/12.
 */
public class ProcessSQL {
    public static void pick(int i, final int arrayCount, BufferSQLContext context, HashArray array, ByteArrayInterface sql) {
        while (i < arrayCount) {
            _QUESTIONMARK_1_0_11(i, arrayCount, context, array, sql);
            int res = _c_b_1_0_29(i, arrayCount, context, array, sql);
            if (res == i) {
                ++i;
            } else {
                i = res;
            }
        }
    }

    public static final int _QUESTIONMARK_11_11_11_quest(int i, final int arrayCount, BufferSQLContext context, HashArray array, ByteArrayInterface sql) {
        if ((i) < arrayCount) {
            {
                i = context.matchPlaceholders(i);
                context.setDynamicAnnotationResult(2);
            }
        }
        return i;
    }

    public static final int _QUESTIONMARK_11_10_11(int i, final int arrayCount, BufferSQLContext context, HashArray array, ByteArrayInterface sql) {
        _QUESTIONMARK_11_11_11_quest(i, arrayCount, context, array, sql);
        return i;
    }

    public static final int _d_9_8_11(int i, final int arrayCount, BufferSQLContext context, HashArray array, ByteArrayInterface sql) {
        if ((i) < arrayCount) {
            if (15L == array.getHash(i)) {
                ++i;
                if ((i) < arrayCount) {
                    if (14L == array.getType(i)) {
                        ++i;
                        _QUESTIONMARK_11_10_11(i, arrayCount, context, array, sql);
                    }
                }
            }
        }
        return i;
    }

    public static final int _QUESTIONMARK_7_7_11_quest(int i, final int arrayCount, BufferSQLContext context, HashArray array, ByteArrayInterface sql) {
        if ((i) < arrayCount) {
            {
                i = context.matchPlaceholders(i);
                if ((i) < arrayCount) {
                    if (21212L == array.getHash(i)) {
                        ++i;
                        i = _d_9_8_11(i, arrayCount, context, array, sql);
                    }
                }
            }
        }
        return i;
    }

    public static final int _EQUAL_6_5_11(int i, final int arrayCount, BufferSQLContext context, HashArray array, ByteArrayInterface sql) {
        if ((i) < arrayCount) {
            if (14L == array.getType(i)) {
                ++i;
                _QUESTIONMARK_7_7_11_quest(i, arrayCount, context, array, sql);
            }
        }
        return i;
    }

    public static final int _QUESTIONMARK_3_3_11_quest(int i, final int arrayCount, BufferSQLContext context, HashArray array, ByteArrayInterface sql) {
        if ((i) < arrayCount) {
            {
                i = context.matchPlaceholders(i);
                if ((i) < arrayCount) {
                    if (21212L == array.getHash(i)) {
                        ++i;
                        if ((i) < arrayCount) {
                            if (14L == array.getHash(i)) {
                                ++i;
                                i = _EQUAL_6_5_11(i, arrayCount, context, array, sql);
                            }
                        }
                    }
                }
            }
        }
        return i;
    }

    public static final int _QUESTIONMARK_3_2_11(int i, final int arrayCount, BufferSQLContext context, HashArray array, ByteArrayInterface sql) {
        _QUESTIONMARK_3_3_11_quest(i, arrayCount, context, array, sql);
        return i;
    }

    public static final int _QUESTIONMARK_1_1_11_quest(int i, final int arrayCount, BufferSQLContext context, HashArray array, ByteArrayInterface sql) {
        {
            {
                i = context.matchPlaceholders(i);
                if ((i) < arrayCount) {
                    if (14L == array.getType(i)) {
                        ++i;
                        _QUESTIONMARK_3_2_11(i, arrayCount, context, array, sql);
                    }
                }
            }
        }
        return i;
    }

    public static final int _QUESTIONMARK_1_0_11(int i, final int arrayCount, BufferSQLContext context, HashArray array, ByteArrayInterface sql) {
        _QUESTIONMARK_1_1_11_quest(i, arrayCount, context, array, sql);
        return i;
    }

    public static final int _QUESTIONMARK_7_7_18_quest(int i, final int arrayCount, BufferSQLContext context, HashArray array, ByteArrayInterface sql) {
        if ((i) < arrayCount) {
            {
                i = context.matchPlaceholders(i);
                context.setDynamicAnnotationResult(1);
            }
        }
        return i;
    }

    public static final int _EQUAL_6_5_18(int i, final int arrayCount, BufferSQLContext context, HashArray array, ByteArrayInterface sql) {
        if ((i) < arrayCount) {
            if (14L == array.getType(i)) {
                ++i;
                _QUESTIONMARK_7_7_18_quest(i, arrayCount, context, array, sql);
            }
        }
        return i;
    }

    public static final int _QUESTIONMARK_3_3_18_quest(int i, final int arrayCount, BufferSQLContext context, HashArray array, ByteArrayInterface sql) {
        if ((i) < arrayCount) {
            {
                i = context.matchPlaceholders(i);
                if ((i) < arrayCount) {
                    if (21212L == array.getHash(i)) {
                        ++i;
                        if ((i) < arrayCount) {
                            if (15L == array.getHash(i)) {
                                ++i;
                                i = _EQUAL_6_5_18(i, arrayCount, context, array, sql);
                            }
                        }
                    }
                }
            }
        }
        return i;
    }

    public static final int _QUESTIONMARK_3_2_18(int i, final int arrayCount, BufferSQLContext context, HashArray array, ByteArrayInterface sql) {
        _QUESTIONMARK_3_3_18_quest(i, arrayCount, context, array, sql);
        return i;
    }

    public static final int _ONE_11_10_29(int i, final int arrayCount, BufferSQLContext context, HashArray array, ByteArrayInterface sql) {
        if ((i) < arrayCount) {
            if (context.matchDigit(i, 1)) {
                ++i;
                context.setDynamicAnnotationResult(3);
            }
        }
        return i;
    }

    public static final int _d_9_8_29(int i, final int arrayCount, BufferSQLContext context, HashArray array, ByteArrayInterface sql) {
        if ((i) < arrayCount) {
            if (15L == array.getHash(i)) {
                ++i;
                if ((i) < arrayCount) {
                    if (14L == array.getType(i)) {
                        ++i;
                        i = _ONE_11_10_29(i, arrayCount, context, array, sql);
                    }
                }
            }
        }
        return i;
    }

    public static final int _EQUAL_6_5_29(int i, final int arrayCount, BufferSQLContext context, HashArray array, ByteArrayInterface sql) {
        if ((i) < arrayCount) {
            if (14L == array.getType(i)) {
                ++i;
                if ((i) < arrayCount) {
                    if (context.matchDigit(i, 1)) {
                        ++i;
                        if ((i) < arrayCount) {
                            if (21212L == array.getHash(i)) {
                                ++i;
                                i = _d_9_8_29(i, arrayCount, context, array, sql);
                            }
                        }
                    }
                }
            }
        }
        return i;
    }

    public static final int _ONE_3_2_29(int i, final int arrayCount, BufferSQLContext context, HashArray array, ByteArrayInterface sql) {
        if ((i) < arrayCount) {
            if (context.matchDigit(i, 1)) {
                ++i;
                if ((i) < arrayCount) {
                    if (21212L == array.getHash(i)) {
                        ++i;
                        if ((i) < arrayCount) {
                            if (14L == array.getHash(i)) {
                                ++i;
                                i = _EQUAL_6_5_29(i, arrayCount, context, array, sql);
                            }
                        }
                    }
                }
            }
        }
        return i;
    }

    public static final int _c_b_1_0_29(int i, final int arrayCount, BufferSQLContext context, HashArray array, ByteArrayInterface sql) {
        {
            if (14L == array.getHash(i)) {
                ++i;
                if ((i) < arrayCount) {
                    if (14L == array.getType(i)) {
                        ++i;
                        _QUESTIONMARK_3_2_18(i, arrayCount, context, array, sql);
                    }
                }
            }
        }
        {
            if (13L == array.getHash(i)) {
                ++i;
                if ((i) < arrayCount) {
                    if (14L == array.getType(i)) {
                        ++i;
                        i = _ONE_3_2_29(i, arrayCount, context, array, sql);
                    }
                }
            }
        }
        return i;
    }
}
