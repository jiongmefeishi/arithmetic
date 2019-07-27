package cn.zqtao.learn.day8;

import cn.zqtao.learn.day8.graph.Graph;
import cn.zqtao.learn.day8.graph.GraphGenerator;
import cn.zqtao.learn.day8.graph.Node;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @auther: zqtao
 * @description: 深度优先遍历
 * 1、利用栈实现
 * 2、从源节点开始吧节点按照深度放入栈，然后弹出
 * 3、每弹出一个点，把该节点下一个没有进过栈的邻接点放入栈
 * 4、直到栈空
 * @version: 1.0
 */
public class Code_47_GraphDFS {
    public static void dfs(Node node) {
        if (node == null) return;

        Stack<Node> stack = new Stack<>();
        // 表示一个点进没进队列,set 是队列的一个注册 ，进过队列的用set保留下来
        HashSet<Node> set = new HashSet<>();

        stack.add(node);
        set.add(node);
        System.out.println(node.value);

        while (!stack.isEmpty()){
            Node cur = stack.pop();

            for(Node next: cur.nexts){
                if (!set.contains(next)){ // 只要有一个邻接点不在set中，就break

                    stack.add(cur);// 重新入栈
                    stack.add(next);

                    set.add(next); // 注册next节点
                    System.out.println(next.value);
                    break;
                }
            }

        }
    }

    public static void main(String[] args) {
        // {from, to, weight}
        Integer[][] matrix = {
                {1, 2, 3},
                {1, 3, 4},
                {1, 4, 2},
                {2, 7, 1},
                {3, 5, 5},
                {4, 6, 1}
        };

        Graph graph = GraphGenerator.createGraph(matrix);
        dfs(graph.nodes.get(1));
        // 1 2 7 3 5 4 6
    }
}
