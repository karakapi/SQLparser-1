package io.mycat.mycat2.sqlparser.byteArrayInterface.dynamicAnnotation;

import io.mycat.mycat2.sqlparser.BufferSQLContext;
import io.mycat.mycat2.sqlparser.SQLParseUtils.HashArray;
import io.mycat.mycat2.sqlparser.byteArrayInterface.ByteArrayInterface;


public class ProcessSQL implements DynamicAnnotationMatch {
    public final void pick(int i, final int arrayCount, BufferSQLContext context, HashArray array, ByteArrayInterface sql) {
//        while (i < arrayCount) {
//            i = _x_y_1_0_29(i, arrayCount, context, array, sql);
//            if (res == i) {
//                ++i;
//            } else {
//                i = res;
//            }
//        }
    }

    public final int pick0(int i, final int arrayCount, BufferSQLContext context, HashArray array, ByteArrayInterface sql) {
        i = _x_y_1_0_29(i, arrayCount, context, array, sql);
        return i;
    }

    public final int _QUESTIONMARK_11_11_11_quest(int i, final int arrayCount, BufferSQLContext context, HashArray array, ByteArrayInterface sql) {
        if ((i) < arrayCount) {
            {
                int start = i;
                i = context.matchPlaceholders(i);
                context.setDynamicAnnotationResult(2);
                pick(i - 1 - 3, arrayCount, context, array, sql);
            }
        }
        return i;
    }

    public final int _QUESTIONMARK_11_10_11(int i, final int arrayCount, BufferSQLContext context, HashArray array, ByteArrayInterface sql) {
        i = _QUESTIONMARK_11_11_11_quest(i, arrayCount, context, array, sql);
        return i;
    }

    public final int _c_9_8_11(int i, final int arrayCount, BufferSQLContext context, HashArray array, ByteArrayInterface sql) {
        if ((i) < arrayCount) {
            if (14L == array.getHash(i)) {
                ++i;
                if ((i) < arrayCount) {
                    if (14L == array.getType(i)) {
                        ++i;
                        i = _QUESTIONMARK_11_10_11(i, arrayCount, context, array, sql);
                    }
                }
            }
        }
        return i;
    }

    public final int _QUESTIONMARK_7_7_11_quest(int i, final int arrayCount, BufferSQLContext context, HashArray array, ByteArrayInterface sql) {
        if ((i) < arrayCount) {
            {
                int start = i;
                i = context.matchPlaceholders(i);
                context.setDynamicAnnotationResult(3);
                pick(i - 1 - 3, arrayCount, context, array, sql);
                if ((i) < arrayCount) {
                    if (21212L == array.getHash(i)) {
                        ++i;
                        i = _c_9_8_11(i, arrayCount, context, array, sql);
                    }
                }
            }
        }
        return i;
    }

    public final int _EQUAL_6_5_11(int i, final int arrayCount, BufferSQLContext context, HashArray array, ByteArrayInterface sql) {
        if ((i) < arrayCount) {
            if (14L == array.getType(i)) {
                ++i;
                i = _QUESTIONMARK_7_7_11_quest(i, arrayCount, context, array, sql);
            }
        }
        return i;
    }

    public final int _QUESTIONMARK_3_3_11_quest(int i, final int arrayCount, BufferSQLContext context, HashArray array, ByteArrayInterface sql) {
        if ((i) < arrayCount) {
            {
                int start = i;
                i = context.matchPlaceholders(i);
                if ((i) < arrayCount) {
                    if (21212L == array.getHash(i)) {
                        ++i;
                        if ((i) < arrayCount) {
                            if (36L == array.getHash(i)) {
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

    public final int _QUESTIONMARK_3_2_11(int i, final int arrayCount, BufferSQLContext context, HashArray array, ByteArrayInterface sql) {
        i = _QUESTIONMARK_3_3_11_quest(i, arrayCount, context, array, sql);
        return i;
    }

    public final int _ONE_9_8_20(int i, final int arrayCount, BufferSQLContext context, HashArray array, ByteArrayInterface sql) {
        if ((i) < arrayCount) {
            if (context.matchDigit(i, 1)) {
                ++i;
                context.setDynamicAnnotationResult(1);
            }
        }
        return i;
    }

    public final int _and_6_5_20(int i, final int arrayCount, BufferSQLContext context, HashArray array, ByteArrayInterface sql) {
        if ((i) < arrayCount) {
            if (21212L == array.getHash(i)) {
                ++i;
                if ((i) < arrayCount) {
                    if (12L == array.getHash(i)) {
                        ++i;
                        if ((i) < arrayCount) {
                            if (14L == array.getType(i)) {
                                ++i;
                                i = _ONE_9_8_20(i, arrayCount, context, array, sql);
                            }
                        }
                    }
                }
            }
        }
        return i;
    }

    public final int _a_3_2_20(int i, final int arrayCount, BufferSQLContext context, HashArray array, ByteArrayInterface sql) {
        if ((i) < arrayCount) {
            if (12L == array.getHash(i)) {
                ++i;
                if ((i) < arrayCount) {
                    if (18L == array.getType(i)) {
                        ++i;
                        if ((i) < arrayCount) {
                            if (13L == array.getHash(i)) {
                                ++i;
                                i = _and_6_5_20(i, arrayCount, context, array, sql);
                            }
                        }
                    }
                }
            }
        }
        return i;
    }

    public final int _ONE_11_10_29(int i, final int arrayCount, BufferSQLContext context, HashArray array, ByteArrayInterface sql) {
        if ((i) < arrayCount) {
            if (context.matchDigit(i, 1)) {
                ++i;
                context.setDynamicAnnotationResult(4);
            }
        }
        return i;
    }

    public final int _a_9_8_29(int i, final int arrayCount, BufferSQLContext context, HashArray array, ByteArrayInterface sql) {
        if ((i) < arrayCount) {
            if (12L == array.getHash(i)) {
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

    public final int _QUESTIONMARK_7_7_29_quest(int i, final int arrayCount, BufferSQLContext context, HashArray array, ByteArrayInterface sql) {
        if ((i) < arrayCount) {
            {
                int start = i;
                i = context.matchPlaceholders(i);
                if ((i) < arrayCount) {
                    if (21212L == array.getHash(i)) {
                        ++i;
                        i = _a_9_8_29(i, arrayCount, context, array, sql);
                    }
                }
            }
        }
        return i;
    }

    public final int _EQUAL_6_5_29(int i, final int arrayCount, BufferSQLContext context, HashArray array, ByteArrayInterface sql) {
        if ((i) < arrayCount) {
            if (14L == array.getType(i)) {
                ++i;
                i = _QUESTIONMARK_7_7_29_quest(i, arrayCount, context, array, sql);
            }
        }
        return i;
    }

    public final int _QUESTIONMARK_3_3_29_quest(int i, final int arrayCount, BufferSQLContext context, HashArray array, ByteArrayInterface sql) {
        if ((i) < arrayCount) {
            {
                int start = i;
                i = context.matchPlaceholders(i);
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

    public final int _QUESTIONMARK_3_2_29(int i, final int arrayCount, BufferSQLContext context, HashArray array, ByteArrayInterface sql) {
        i = _QUESTIONMARK_3_3_29_quest(i, arrayCount, context, array, sql);
        return i;
    }

    public final int _x_y_1_0_29(int i, final int arrayCount, BufferSQLContext context, HashArray array, ByteArrayInterface sql) {
        {
            if (35L == array.getHash(i)) {
                ++i;
                if ((i) < arrayCount) {
                    if (14L == array.getType(i)) {
                        ++i;
                        i = _QUESTIONMARK_3_2_11(i, arrayCount, context, array, sql);
                    }
                }
            }
        }
        {
            if (36L == array.getHash(i)) {
                ++i;
                if ((i) < arrayCount) {
                    if (14L == array.getType(i)) {
                        ++i;
                        i = _a_3_2_20(i, arrayCount, context, array, sql);
                        i = _QUESTIONMARK_3_2_29(i, arrayCount, context, array, sql);
                    }
                }
            }
        }
        return i;
    }

}