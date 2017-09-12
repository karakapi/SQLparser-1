package io.mycat.mycat2.sqlparser;

import io.mycat.mycat2.sqlparser.SQLParseUtils.HashArray;
import io.mycat.mycat2.sqlparser.byteArrayInterface.dynamicAnnotation.DynamicAnnotationRuntime;
import io.mycat.mycat2.sqlparser.byteArrayInterface.dynamicAnnotation.ProcessSQL;
import io.mycat.mycat2.sqlparser.byteArrayInterface.dynamicAnnotation.Tries;
import io.mycat.mycat2.sqlparser.byteArrayInterface.dynamicAnnotation.TriesContext;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jamie on 2017/9/5.
 */
public class DynamicAnnotationTest extends TestCase {
    //    public static void main(String[] args) throws Exception{
//
//    }
    @Test
    public void test1() throws Exception {
        String t = "aaaaa b = 1 and c = 1 and d = 1";
        test(t);
    }

    public static void insert(String str, Tries tries, String mark) {
        BufferSQLParser parser = new BufferSQLParser();
        BufferSQLContext context = new BufferSQLContext();
        parser.parse(str.getBytes(), context);
        HashArray hashArray = context.getHashArray();
        Tries.insertNode(context, tries, mark);
    }

    public static void insert(String str, Tries tries) {
        insert(str, tries, str);
    }
    DynamicAnnotationRuntime runtime;
    //    SQLParser parser;
    BufferSQLParser parser;
    BufferSQLContext context;
    private static final Logger LOGGER = LoggerFactory.getLogger(TCLSQLParserTest.class);

    private void test(String t) {
        LOGGER.info(t);
        byte[] bytes = t.getBytes();
        parser.parse(bytes, context);
        ProcessSQL.pick(0, context.getHashArray().getCount(), context, context.getHashArray(), context.getBuffer());
        runtime.printCallBackInfo(context);
    }

    @Before
    protected void setUp() throws Exception {
        parser = new BufferSQLParser();
        context = new BufferSQLContext();
        //parser.init();
//        MatchMethodGenerator.initShrinkCharTbl();
//        Tries tries = new Tries();
//        insert("hhh g name = ?  and id between 1 and 3", tries);
//        //   DAExprSQLParser.pickExpr(0,context.getHashArray().getCount(),context,context.getHashArray(),context.getBuffer());
//        insert("hhh g name = sss id ", tries);
//        insert("hhh g name = ? and id =?", tries);
//        insert("? g name", tries);
//        insert("c = ? and d = ?", tries);
//        insert("b = 1 and c = 1 and d = 1", tries);
//        //     DAExprSQLParser.pickExpr(0,context.getHashArray().getCount(),context,context.getHashArray(),context.getBuffer());
//        TriesContext context = new TriesContext();
//        System.out.println(tries.toCode2(true, context));
//        context.funList.stream().distinct().forEach(System.out::println);
        //c = ([a-z0-9A-Z_$]|[a-zA-Z_$].[a-zA-Z_$]) and d = ([a-z0-9A-Z_$]|[a-zA-Z_$].[a-zA-Z_$])
        MatchMethodGenerator.initShrinkCharTbl();
        List<String> list = new ArrayList<>();
        list.add("b = 1 and c = 1 and d = 1");
        list.add("c = ? and d = ?");
        list.add("? = ? and c = ? and d = ?");
        Map<String, Set<String>> map = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                String q = list.get(i);
                String w = list.get(j);
                if (q.length() <= w.length()) {
                    if (q.contains("?")) {
                        if (isIn(q, w)) {
                            map.compute(w, (s, l) -> {
                                if (l == null) {
                                    Set<String> list1 = new HashSet<>();
                                    list1.add(s);
                                    list1.add(q);
                                    return list1;
                                } else {
                                    l.add(q);
                                    return l;
                                }
                            });
                            continue;
                        }
                    }
                }
                Set<String> set = new HashSet<>();
                set.add(w);
                map.computeIfAbsent(w, (s) -> set);
                continue;
            }
        }
        Map<Integer, String> int2str = new HashMap<>();
        Map<String, Integer> str2Int = new HashMap<>();
        Iterator<String> it = map.keySet().iterator();
        for (int i = 1; it.hasNext(); i++) {
            String key= it.next();
            int2str.put(i,key);
            str2Int.put(key,i);
        }
        Tries tries = new Tries();
        for (Map.Entry<String,Integer> i:str2Int.entrySet()){
            insert(i.getKey(), tries,i.getValue().toString());
        }
        runtime=new DynamicAnnotationRuntime(map,int2str,str2Int);
    }

    public static boolean isIn(String f, String s) {
        f = f.replace("?", "([a-z0-9A-Z_$]|[a-zA-Z_$].[a-zA-Z_$]|\\?)");
        Matcher matcher = Pattern.compile(f).matcher(s);
        return matcher.find();
    }

    public static void main(String[] args) {
        MatchMethodGenerator.initShrinkCharTbl();
        List<String> list = new ArrayList<>();
        list.add("b = 1 and c = 1 and d = 1");
        list.add("c = ? and d = ?");
        list.add("? = ? and c = ? and d = ?");
        Map<String, Set<String>> map = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                String q = list.get(i);
                String w = list.get(j);
                if (q.length() <= w.length()) {
                    if (q.contains("?")) {
                        if (isIn(q, w)) {
                            map.compute(w, (s, l) -> {
                                if (l == null) {
                                    Set<String> list1 = new HashSet<>();
                                    list1.add(s);
                                    list1.add(q);
                                    return list1;
                                } else {
                                    l.add(q);
                                    return l;
                                }
                            });
                            continue;
                        }
                    }
                }
                Set<String> set = new HashSet<>();
                set.add(w);
                map.computeIfAbsent(w, (s) -> set);
                continue;
            }
        }
        Map<Integer, String> int2str = new HashMap<>();
        Map<String, Integer> str2Int = new HashMap<>();
        Iterator<String> it = map.keySet().iterator();
        for (int i = 1; it.hasNext(); i++) {
            String key= it.next();
            int2str.put(i,key);
            str2Int.put(key,i);
        }
        Tries tries = new Tries();
       for (Map.Entry<String,Integer> i:str2Int.entrySet()){
           insert(i.getKey(), tries,i.getValue().toString());
       }
        TriesContext context = new TriesContext();
        System.out.println(tries.toCode2(true, context));
        context.funList.stream().distinct().forEach(System.out::println);
    }
}
