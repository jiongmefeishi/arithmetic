package cn.zqtao.examintion;

/**
 * @auther: zqtao
 * @description: 寻找子串
 * 在线考试
 * 编程题 | 20.0分1/2
 * 寻找子串
 * 时间限制：C/C++语言 1000MS；其他语言 3000MS
 * 内存限制：C/C++语言 65536KB；其他语言 589824KB
 * 题目描述：
 * 英语老师看你老是在英语课上和周围同学交头接耳，所以给你布置了一份额外的家庭作业来惩罚惩罚你：
 * 你有一个字符串s，请你在s的所有子串中，找到出现次数最多的子串，告诉老师它的出现次数。
 *
 * 输入
 * 共一行，一个字符串s，仅由英文小写字母组成，1≤|s|≤10000。
 *
 * 输出
 * 一个正整数，表示最大出现次数。
 *
 *
 * 样例输入
 * aba
 * 样例输出
 * 2
 *
 * 提示
 * aba的所有子串为a、b、a、ab、ba、aba，其中a的出现次数最多，出现了2次。
 *
 * 我使用的是前缀树，在寻找所有的子串过程时间超时了
 */
public class Code_03_FindSubStr {

    public static class TrieNode {
        public int endNum;
        public TrieNode[] nexts;
        public TrieNode() {
            this.endNum = 0;
            this.nexts = new TrieNode[26];
        }
    }

    public static class Trie {
        private TrieNode root;
        public Trie() {
            root = new TrieNode();
        }
        public void insert(String word) {
            if (word == null) return;
            TrieNode node = this.root;

            char[] chars = word.toCharArray();
            int index = 0;

            for (int i = 0; i < chars.length; i++) {
                index = chars[i] - 'a';
                if (node.nexts[index] == null) {
                    node.nexts[index] = new TrieNode();
                }

                node = node.nexts[index];
            }
            node.endNum++;
        }

        public int search(String word) {
            if (word == null || word.equals("")) return 0;

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
    }

    public static int find(String str) {
        Trie trie = new Trie();

        for (int i = 0; i < str.length(); i++) {
            for (int j = i + 1; j <= str.length(); j++) {
                trie.insert(str.substring(i, j));
            }
        }

        int max = 0;
        for (int i = 0; i < str.length(); i++) {
            for (int j = i + 1; j <= str.length(); j++) {
                max = Math.max(max, trie.search(str.substring(i, j)));
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(find(""));
        System.out.println(find("aba"));
        System.out.println(find("ab"));
        System.out.println(find("aa"));
        System.out.println(find("ababbba"));
    }
}
