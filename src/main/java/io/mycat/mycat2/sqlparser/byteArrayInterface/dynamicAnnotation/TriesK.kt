//package io.mycat.mycat2.sqlparser.byteArrayInterface.dynamicAnnotation
//
//import io.mycat.mycat2.sqlparser.BufferSQLContext
//import io.mycat.mycat2.sqlparser.SQLParseUtils.HashArray
//import io.mycat.mycat2.sqlparser.byteArrayInterface.ByteArrayInterface
//import io.mycat.mycat2.sqlparser.byteArrayInterface.Tokenizer2
//
//import java.util.ArrayList
//import java.util.HashMap
//
//class TriesK {
//
//    internal var isTrie: Boolean = false
//    internal var children = HashMap<Key, TriesK>()
//    internal var callback: MutableList<Runnable>? = null
//
//    override fun toString(): String {
//        return "TrieCompiler{" +
//                "isTrie=" + isTrie +
//                ", children=" + children +
//                ", callback=" + callback +
//                '}'
//    }
//
//    companion object {
//
//        fun insertNode(context: BufferSQLContext, head: TriesK, runnable: Runnable): Boolean {
//            val array = context.hashArray
//            val byteArray = context.buffer
//            if (array == null || array.count == 0)
//                return false
//            //如果插入的单词为null 或者单词长度为0直接返回false，false代表该单词不是前缀树中某个单词的前缀，
//            //或者前缀树中某个单词是该单词的前缀。
//            var i = 0
//            var cur = head
//            //将字符串的每个字符插入到前缀树中
//            val length = array.count
//            while (i < length) {
//                val c = Key(array.getType(i), array.getHash(i), byteArray.getString(i, array))
//                if (!cur.children.containsKey(c)) {
//                    val tries = TriesK()
//                    cur.children.put(c, tries)
//                    //如果当前节点中的子树节点中不包含当前字符，新建一个子节点。
//                }
//                //否则复用该节点
//                cur = cur.children[c]
//                if (cur.isTrie == true) {
//                    doCallback(cur, runnable)
//                    println(" trie tree")
//                    return true
//                    //判断前缀树中是否有字符串为当前字符串的前缀。
//                }
//                i++
//            }
//            cur.isTrie = true
//            if (cur.children.size > 0) {
//                doCallback(cur, runnable)
//                println(" trie tree")
//                return true
//                //判断当前字符串是否是前缀树中某个字符的前缀。
//            }
//            doCallback(cur, runnable)
//            return false
//        }
//
//        fun doCallback(head: TriesK, runnable: Runnable) {
//            if (head.callback == null) {
//                head.callback = ArrayList()
//            }
//            head.callback!!.add(runnable)
//        }
//
//        @JvmStatic
//        fun main(args: Array<String>) {
//            val tries = TriesK()
//
//        }
//    }
//
//        public String toCode() {
//            this.children.entrySet().stream().map((i) -> {
//                switch (i.getKey().getType()) {
//                    case Tokenizer2.CHARS:{
//                        "if("+i.getKey().getLongHash()+"==array.getHash(i)){" +
//                                "" +
//                                "" +
//                                "}"
//                        break;
//                    }
//                    case Tokenizer2.STRINGS:{
//                        break;
//                    }
//                    case Tokenizer2.DIGITS:{
//                        break;
//                    }
//                    case Tokenizer2.QUESTION_MARK:{
//                        break;
//                    }
//                    default:
//                        break;
//                }
//            })
//        }
//}