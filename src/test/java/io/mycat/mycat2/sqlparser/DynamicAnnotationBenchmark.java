package io.mycat.mycat2.sqlparser;

import io.mycat.mycat2.sqlparser.byteArrayInterface.DefaultByteArray;
import io.mycat.mycat2.sqlparser.byteArrayInterface.dynamicAnnotation.DynamicAnnotationMatch;
import io.mycat.mycat2.sqlparser.byteArrayInterface.dynamicAnnotation.DynamicAnnotationRuntime;
import io.mycat.mycat2.sqlparser.byteArrayInterface.dynamicAnnotation.DynamicAnnotationUtil;
import org.junit.Test;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by jamie on 2017/9/14.
 */
@BenchmarkMode(Mode.Throughput)//基准测试类型
@OutputTimeUnit(TimeUnit.SECONDS)//基准测试结果的时间类型
@Warmup(iterations = 20)//预热的迭代次数
@Threads(1)//测试线程数量
@State(Scope.Thread)//该状态为每个线程独享
//度量:iterations进行测试的轮次，time每轮进行的时长，timeUnit时长单位,batchSize批次数量
@Measurement(iterations = 10, time = -1, timeUnit = TimeUnit.SECONDS, batchSize = -1)
public class DynamicAnnotationBenchmark {
    DynamicAnnotationRuntime runtime;
    DynamicAnnotationMatch match;
    //run
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(DynamicAnnotationBenchmark.class.getSimpleName())
                .forks(1)
                //     使用之前要安装hsdis
                //-XX:-TieredCompilation 关闭分层优化 -server
                //-XX:+LogCompilation  运行之后项目路径会出现按照测试顺序输出hotspot_pid<PID>.log文件,可以使用JITWatch进行分析,可以根据最后运行的结果的顺序按文件时间找到对应的hotspot_pid<PID>.log文件
                //.jvmArgs("-XX:+UnlockDiagnosticVMOptions", "-XX:+LogCompilation", "-XX:+TraceClassLoading", "-XX:+PrintAssembly")
                //  .addProfiler(CompilerProfiler.class)    // report JIT compiler profiling via standard MBeans
                //  .addProfiler(GCProfiler.class)    // report GC time
                // .addProfiler(StackProfiler.class) // report method stack execution profile
                // .addProfiler(PausesProfiler.class)
                /*
                WinPerfAsmProfiler
                You must install Windows Performance Toolkit. Once installed, locate directory with xperf.exe file
                and either add it to PATH environment variable, or set it to jmh.perfasm.xperf.dir system property.
                 */
                //.addProfiler(WinPerfAsmProfiler.class)
                //更多Profiler,请看JMH介绍
                //.output("InstructionsBenchmark.log")//输出信息到文件
                .build();
        new Runner(opt).run();
    }



    @Benchmark
    public void test1() throws Exception {
//        String t = "b = 1 and c = 1 and d = 1 and c = 1";
        runtime.getMatch().pick(0, context.getHashArray().getCount(), context, context.getHashArray(), context.getBuffer());
    }
//    @Benchmark
//    public void test2() throws Exception {
//        /*
//           list.add("x = ? and y = ? and c = ?");
//        list.add("y = ? and c = ? and a = 1");
//         */
//        String t = "x = 4 and y = 3 and c = 2 and a =1";
//        test(t);
//    }
//    @Benchmark
//    public void test5() throws Exception {
//        /*
//           list.add("x = ? and y = ? and c = ?");
//        list.add("y = ? and c = ? and a = 1");
//         */
//        String t = "x = 4 and y = 3 and c = 2 and a =1";
//        test(t);
//    }
    BufferSQLParser parser;
    BufferSQLContext context;

    @Setup
    public void init()throws Exception {
        parser = new BufferSQLParser();
        context = new BufferSQLContext();


       // byte[] bytes = "b = 1 and c = 1 and d = 1 and c = 1 and x = 4 and y = 3 and c = 2 and a =1".getBytes();
        byte[] bytes = "  runtime getMatch() pick ( 0, context . getHashArray () .getCount ( )   , context , context . getHashArray ( ), context . getBuffer () );".getBytes();
        parser.parse(bytes, context);
//        runtime.getMatch().pick(0, context.getHashArray().getCount(), context, context.getHashArray(), context.getBuffer());
//        runtime.printCallBackInfo(context);
//
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
        match=runtime.getMatch();
        System.out.println("=> init");
    }
}
