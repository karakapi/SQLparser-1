package io.mycat.mycat2.sqlparser;

import io.mycat.mycat2.sqlparser.SQLParseUtils.HashArray;
import io.mycat.mycat2.sqlparser.byteArrayInterface.dynamicAnnotation.*;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by jamie on 2017/9/5.
 */
public class DynamicAnnotationTest extends TestCase {
    //    public static void main(String[] args) throws Exception{
//
//    }
    @Test
    public void test1() throws Exception {
        String t = "b = 1 and c = 1 and d = 1 and c = 1";
        test(t);
    }
    @Test
    public void test2() throws Exception {
        /*
           list.add("x = ? and y = ? and c = ?");
        list.add("y = ? and c = ? and a = 1");
         */
        String t = "x = 4 and y = 3 and c = 2 and a =1";
        test(t);
    }
    @Test
    public void test5() throws Exception {
        /*
           list.add("x = ? and y = ? and c = ?");
        list.add("y = ? and c = ? and a = 1");
         */
        String t = "x = 4 and y = 3 and c = 2 and a =1";
        test(t);
    }
    @Test
    public void test6() throws Exception {
        /*
           list.add("x = ? and y = ? and c = ?");
        list.add("y = ? and c = ? and a = 1");
         */
        String t = "b = 1 and c = 1 and d = 1 and c = 1 and x = 4 and y = 3 and c = 2 and a =1";
        test(t);
    }



    public static void insert(String str, TrieCompiler trieCompiler, String mark, int backPos) {
        BufferSQLParser parser = new BufferSQLParser();
        BufferSQLContext context = new BufferSQLContext();
        parser.parse(str.getBytes(), context);
        HashArray hashArray = context.getHashArray();
        TrieCompiler.insertNode(context, trieCompiler, mark, backPos);
    }

    public static void insert(String str, TrieCompiler trieCompiler) {
        insert(str, trieCompiler, str, 0);
    }
    DynamicAnnotationRuntime runtime;
    //    SQLParser parser;
    BufferSQLParser parser;
    BufferSQLContext context;
    private static final Logger LOGGER = LoggerFactory.getLogger(TCLSQLParserTest.class);

    private void test(String t) throws Exception{
        LOGGER.info(t);
        byte[] bytes = t.getBytes();
        parser.parse(bytes, context);
        runtime.getMatch().pick(0, context.getHashArray().getCount(), context, context.getHashArray(), context.getBuffer());
        runtime.printCallBackInfo(context);
    }

    @Before
    protected void setUp() throws Exception {
        parser = new BufferSQLParser();
        context = new BufferSQLContext();
        MatchMethodGenerator.initShrinkCharTbl();
        List<String> list = new ArrayList<>();
        list.add("b = 1 and c = 1 and d = 1");
        list.add("c = ? and d = ?");
        list.add("? = ? and c = ? and d = ?");
        list.add("? = ?");
        list.add("c = ?");
        list.add("d = 1 and c = 1");
        list.add("d = ? and c = 1");
        list.add("a = ? and c = 1");
        list.add("f = 2 and a = b.d");
        list.add("x = ? and y = ?");
        list.add("y = a.b and a = 1");
        list.add("x = ? and y = ? and c = ?");
        list.add("y = ? and c = ? and a = 1");
        list.add("b = 1 and c = 1 and d = 1");
        list.add("c = ? and d = ?");
        list.add("? = ? and c = ? and d = ?");
        list.add("? = ?");
        list.add("c = ?");
        list.add("d = 1 and c = 1");
        list.add("d = ? and c = 1");
        list.add("a = ? and c = 1");
        list.add("f = 2 and a = b.d");
        list.add("x = 1 and y = a.b");
        list.add("y = a.b and a = 1");
         runtime= DynamicAnnotationUtil.compile(list);

    }

    public static boolean isIn(String f, String s) {
        f = f.replace("?", "([a-z0-9A-Z_$]|\\?)");
        Matcher matcher = Pattern.compile(f).matcher(s);
        return matcher.find();
    }

    public static void main(String[] args) throws Exception{
        MatchMethodGenerator.initShrinkCharTbl();
        List<String> list = new ArrayList<>();
//        list.add("b = 1 and c = 1 and d = 1");
//        list.add("c = ? and d = ?");
//        list.add("? = ? and c = ? and d = ?");
//        list.add("? = ?");
//        list.add("c = ?");
//        list.add("d = 1 and c = 1");
//        list.add("d = ? and c = 1");
//        list.add("a = ? and c = 1");
//        list.add("f = 2 and a = b.d");
        list.add("x = ? and y = ?");
        list.add("y = a.b and a = 1");
        list.add("x = ? and y = ? and c = ?");
        list.add("y = ? and c = ? and a = 1");
       DynamicAnnotationRuntime runtime= DynamicAnnotationUtil.compile(list);
    }
}
