package cn.zqtao.learn.nowcode_base.day6;

/**
 * @auther: zqtao
 * @description:
 * @version: 1.0
 */
public class Code_36_TrieTree {

    // 前缀树数据项结构
    public static class TrieNode {
        public int pathNum; // 字符串滑过当前节点是次数
        public int endNum; // 以此节点结尾的字符串个数

        //        public HashMap<Character, TrieNode> nexts; // 结构复杂使用map
        public TrieNode[] nexts; // 这里为了方便使用数组，假设涉及数据都是小写的26个字母

        public TrieNode() {
            this.pathNum = 0;
            this.endNum = 0;
            this.nexts = new TrieNode[26];
        }
    }

    // 前缀树
    public static class Trie {
        private TrieNode root; // 前缀树加上数据项

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
            if (word == null) return;
            TrieNode node = this.root;

            char[] chars = word.toCharArray();
            int index = 0; // 用来标记路线，默认26个字母，每个节点26条路线

            for (int i = 0; i < chars.length; i++) {
                index = chars[i] - 'a';
                if (node.nexts[index] == null) {
                    node.nexts[index] = new TrieNode();
                }

                node = node.nexts[index];
                node.pathNum++;
            }
            node.endNum++;// 结尾节点
        }

        public void delete(String word) {
            if (search(word) != 0) {
                TrieNode node = this.root;
                char[] chars = word.toCharArray();
                int index = 0;

                for (int i = 0; i < chars.length; i++) {
                    index = chars[i] - 'a';
                    if (--node.nexts[index].pathNum == 0) { // 如果节点滑过次数只有一次，直接置为null，后序节点无需再遍历
                        node.nexts[index] = null;
                        return;
                    }
                    node = node.nexts[index];
                }
                node.endNum--;
            }
        }


        // 查找是否存在某个字符串
        public int search(String word) {
            if (word == null) return 0;

            TrieNode node = this.root;
            char[] chars = word.toCharArray();
            int index = 0;
            for (int i = 0; i < chars.length; i++) {
                index = chars[i] - 'a';
                if (node.nexts[index] == null) {
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.endNum;
        }

        public int prefixNumber(String pre) {
            if (pre == null) {
                return 0;
            }
            char[] chs = pre.toCharArray();
            TrieNode node = root;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = chs[i] - 'a';
                if (node.nexts[index] == null) {
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.pathNum;
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        System.out.println(trie.search("test"));
        trie.insert("test");
        System.out.println(trie.search("test"));
        trie.delete("test");
        System.out.println(trie.search("test"));
        trie.insert("test");
        trie.insert("test");
        trie.delete("test");
        System.out.println(trie.search("test"));
        trie.delete("test");
        System.out.println(trie.search("test"));
        trie.insert("testa");
        trie.insert("testac");
        trie.insert("testab");
        trie.insert("testad");
        trie.delete("testa");
        System.out.println(trie.search("testa"));
        System.out.println(trie.prefixNumber("test"));

    }
}
