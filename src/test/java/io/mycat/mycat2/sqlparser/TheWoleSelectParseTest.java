package io.mycat.mycat2.sqlparser;

import io.mycat.mycat2.sqlparser.byteArrayInterface.ByteArrayInterface;
import io.mycat.mycat2.sqlparser.byteArrayInterface.SelectStatementParser;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by jamie on 2017/9/8.
 */
public class TheWoleSelectParseTest  extends TestCase {

    @Test
    public void testExpr() throws Exception {
        String t = "select a.id,a.price,b.username,c.haha where a.id in(111,222,333,444) and b.id2=a.id3 " +
                "and a.id3 in(666,777) " +
                "and b.id3=c.haha;";
        String stack="and = a.id3  b.id2 in 444 333 222 111 a.id ";
       /*

                                                              and
                                                             /   \
select a.id,a.price where a.id in(111,222,333,444) ->a.id3        b.id2<-  select b.username
        */
        test(t);
        System.out.println("context.colomnMap.toString():" + context.colomnMap.toString());
        System.out.println("context.asMap.toString():" + context.asMap.toString());
        System.out.println("context.selectItemStart:" + context.selectItemStart);
        System.out.println("context.selectItemEnd:" + context.selectItemEnd);
        System.out.println("context.whereStart:" + context.whereStart);
        System.out.println("context.whereEnd:" + context.whereEnd);
        ByteArrayInterface sql = context.getBuffer();
        String firstStart = t.substring(0, context.selectItemStart);
        String firstEnd = t.substring(context.selectItemEnd, context.whereStart);
        String tail = t.substring(context.whereEnd, context.getBuffer().length());
        System.out.println(firstStart);
        System.out.println(firstEnd + tail);
        Map<String,List<String>> eachList= context.getColomnMap().stream()
                .collect(Collectors.groupingBy(s->{
                  String res=  s.substring(0,s.indexOf("."));
                  return res;
                        },
                        Collector.of(()->{List<String> res=new ArrayList<>();return res;},
                                (l,r)->{l.add(r);},(l,r)->{l.addAll(r);return l;})));
        eachList.entrySet().stream().map((i)->firstStart+" "+i.getValue().stream().distinct().collect(Collectors.joining(","))+firstEnd+tail)
                .forEach(System.out::println);
        System.out.println(context.getOperandStack().toString());
    }

    BufferSQLParser parser;
    BufferSQLContext context;
    private static final Logger LOGGER = LoggerFactory.getLogger(TCLSQLParserTest.class);

    private void test(String t) {
        LOGGER.info(t);
        byte[] bytes = t.getBytes();
        parser.parse(bytes, context);
        SelectStatementParser.pickSelectStatement(0,context.getHashArray().getCount(),context,context.getHashArray(),context.getBuffer());

    }

    @Before
    protected void setUp() throws Exception {
        parser = new BufferSQLParser();
        context = new BufferSQLContext();
        //parser.init();
        MatchMethodGenerator.initShrinkCharTbl();
    }
}
