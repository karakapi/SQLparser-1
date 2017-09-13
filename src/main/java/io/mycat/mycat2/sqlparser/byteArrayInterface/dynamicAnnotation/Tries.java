package io.mycat.mycat2.sqlparser.byteArrayInterface.dynamicAnnotation;

import io.mycat.mycat2.sqlparser.BufferSQLContext;
import io.mycat.mycat2.sqlparser.SQLParseUtils.HashArray;
import io.mycat.mycat2.sqlparser.byteArrayInterface.ByteArrayInterface;
import io.mycat.mycat2.sqlparser.byteArrayInterface.Tokenizer2;

import java.util.*;
import java.util.stream.Collectors;

public class Tries {

    boolean isTrie;
    HashMap<Key, Tries> children = new HashMap<>();
    List<String> callback;
    int backPos =0;

    public static boolean insertNode(BufferSQLContext context, Tries head, String runnable,int backPos) {
        HashArray array = context.getHashArray();
        ByteArrayInterface byteArray = context.getBuffer();
        if (array == null || array.getCount() == 0)
            return false;
        //如果插入的单词为null 或者单词长度为0直接返回false，false代表该单词不是前缀树中某个单词的前缀，
        //或者前缀树中某个单词是该单词的前缀。
        int i = 0;
        Tries cur = head;
        //将字符串的每个字符插入到前缀树中
        int length = array.getCount();
        while (i < length) {
            Key c = new Key(array.getType(i), array.getHash(i), byteArray.getString(i, array));
            if (!cur.children.containsKey(c)) {
                Tries tries = new Tries();
                cur.children.put(c, tries);
                //如果当前节点中的子树节点中不包含当前字符，新建一个子节点。
            }
            //否则复用该节点
            cur = cur.children.get(c);
            if (cur.isTrie == true) {
                cur.backPos=backPos;
                doCallback(cur, runnable);
                System.out.println(" trie tree");
                return true;
                //判断前缀树中是否有字符串为当前字符串的前缀。
            }
            i++;
        }
        cur.isTrie = true;
        if (cur.children.size() > 0) {
            cur.backPos=backPos;
            doCallback(cur, runnable);
            System.out.println(" trie tree");
            return true;
            //判断当前字符串是否是前缀树中某个字符的前缀。
        }
        cur.backPos=backPos;
        doCallback(cur, runnable);
        return false;
    }

    public static void doCallback(Tries head, String runnable) {
        if (head.callback == null) {
            head.callback = new ArrayList<>();
        }
        head.callback.add(runnable);
    }

    public static void main(String[] args) {
        Tries tries = new Tries();
    }

    @Override
    public String toString() {
        return "Tries{" +
                "isTrie=" + isTrie +
                ", children=" + children +
                ", callback=" + callback +
                '}';
    }

    public String toCode2(boolean isRoot, TriesContext context) {
        context.x += 1;
        Map<Boolean, List<Map.Entry<Key, Tries>>> map = this.children.entrySet().stream().collect(Collectors.partitioningBy((k) -> k.getKey().getType() == Tokenizer2.QUESTION_MARK));
        String l = toCode3(isRoot, map.get(Boolean.FALSE), context);
        String r = toCode3(isRoot, map.get(Boolean.TRUE), context);
        context.x -= 1;
        return l + r;
    }

    public String toCode3(boolean isRoot, List<Map.Entry<Key, Tries>> entrySet, TriesContext context) {

        if (context.x % 3 == 0 || context.y % 5 == 0) {
            String res = toCode4(isRoot, entrySet, context);
            if (!"".equals(res.trim())) {
                String funName = context.genFun(entrySet.stream().map((s) -> Ascll.shiftAscll(s.getKey().getText(), false)).collect(Collectors.joining("_"))) + "_" + context.index;
                context.funList.add("public static final int " + funName + "(int i, final int arrayCount, BufferSQLContext context, HashArray array, ByteArrayInterface sql){" + res + "return i;}");

                    return "i=" + funName + "(i, arrayCount, context, array, sql);";


            }
            return "";
        } else {
            return toCode4(isRoot, entrySet, context);
        }
    }

    public String toCode4(boolean isRoot, List<Map.Entry<Key, Tries>> entrySet, TriesContext context) {
        StringBuilder stringBuilder = new StringBuilder();
        context.y += 1;
        for (Map.Entry<Key, Tries> i : entrySet) {
            context.index += 1;
            String q = isRoot ? "{" : "if ((i)<arrayCount){";
            int type = i.getKey().getType();
            switch (type) {
                case Tokenizer2.DIGITS: {
                    q += "if(context.matchDigit(i," + i.getKey().getText() + ")){";
                    q += "++i;";
                    break;
                }
                case Tokenizer2.QUESTION_MARK: {
                    q += "{i=context.matchPlaceholders(i);";
                    break;
                }
                default: {
                    if (i.getKey().longHash == 0) {
                        q += "if(" + i.getKey().getType() + "L==array.getType(i)){";
                    } else {
                        q += "if(" + i.getKey().getLongHash() + "L==array.getHash(i)){";
                    }
                    q += "++i;";
                    break;
                }
            }

            List<String> callback = i.getValue().callback;
            String w="";
            if (callback != null && callback.size() != 0) {
                for (int j = 0; j <callback.size() ; j++) {
                    w += "context.setDynamicAnnotationResult("+callback.get(j)+");";
                }
                if (i.getValue().backPos!=0){
                    w+="pick(i-1-" +i.getValue().backPos+
                            ", arrayCount, context, array, sql);";
                }

            }
            String e = i.getValue().toCode2(false, context);
            String r = "}}";
            stringBuilder.append(q + w + e + r);
            if (type == Tokenizer2.QUESTION_MARK) {
                String funName = context.genFun(entrySet.stream().map((s) -> Ascll.shiftAscll(s.getKey().getText(), false)).collect(Collectors.joining("_"))) + "_" + context.index;
                funName += "_quest";
                context.funList.add("public static final int " + funName + "(int i, final int arrayCount, BufferSQLContext context, HashArray array, ByteArrayInterface sql){" + stringBuilder.toString() + "return i;}");
                stringBuilder.setLength(0);
                stringBuilder.append("i=" + funName + "(i, arrayCount, context, array, sql);");
            }
        }
        context.y -= 1;
        return stringBuilder.toString();
    }

    public static String getRandomString(int length) { //length表示生成字符串的长度
        String base = "abcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}