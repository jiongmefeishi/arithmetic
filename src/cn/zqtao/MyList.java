package cn.zqtao;

import java.util.Arrays;
import java.util.HashMap;

public class MyList {
    private Node head;// 单链表的头
    private Node tail;// 单链表的尾
    private int length;//链表长度

    public void addNode(int value) {
        if (head == null) {
            head = new Node(value);
            tail = head;
        } else {
            tail.next = new Node(value);
            tail = tail.next;
        }
        length++;
    }

    public Node getNode(int index) {
        if (index < 0 || index >= length) {
            return null;
        }
        Node node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    public int loopLen() {
        int stepCount1 = 0, stepCount2 = 0;
        Node t2 = head, t1 = head;
        int i = 1;//第i次相遇
        do {
            try {//如果出现空指针异常，则没有环
                while ((t2 = t2.next.next) != (t1 = t1.next)) {
                    if (i == 1) {
                        stepCount1++;
                        stepCount2++;
                    } else {
                        stepCount2++;
                    }
                }
            } catch (
                    Exception e) {
                return 0;
            }

            i++;
        }
        while (i <= 2);
        return (stepCount2 - stepCount1 + 1);
    }

    public static void main(String[] args) {
//        MyList myList = new MyList();        // 创建链表

//        for (int i = 0; i < 10; i++) {
////            myList.addNode(i);
//        }                //把节点9的next指向节点3
//        Node node9 = myList.getNode(9);
//        Node node3 = myList.getNode(3);
//        node9.next = node3;
//        System.out.println(myList.loopLen());

        String str = "[[1,2],[2,3],[3,4],[4,5],[5,3]]";
        getArr(str);
    }

    public static Node getList(int[] arr) {

//        [[1,2],[2,3],[3,4],[4,5],[5,3]]
//        []

        Node head = null;
        Node tmp = head;

        HashMap<Node, Node> map = new HashMap<>();
        map.put(head, null);

        for (int i = 0; i < arr.length; i += 2) {
            int a = arr[i];
            int b = arr[i + 1];

            Node na = new Node(a);
            Node nb = new Node(b);
            if (map.containsKey(na)) {
                na.next = nb;
                map.put(na, nb);
                map.put(nb, null);
            }else {
                map.put(na, null);
                map.put(nb, null);
                na.next = nb;
                map.put(na, nb);
            }

            head.next = new Node(a);
            head = head.next;

        }



        return head;
    }

    public static void getArr(String str) {
        String replace1 = str.replace("[", "");
        replace1 = replace1.replace("]", "");

        String[] s = replace1.split(",");
        int[] arr = new int[s.length];
        for (int i = 0; i < s.length; i++) {
            arr[i] = Integer.parseInt(s[i]);
        }
        System.out.println(Arrays.toString(arr));
    }


    static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;

        }
    }
}