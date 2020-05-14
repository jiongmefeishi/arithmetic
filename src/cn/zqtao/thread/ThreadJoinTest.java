package cn.zqtao.thread;

/**
 * @auther: zqtao
 * @description: )现在有 T1、T2、T3 三个线程，你怎样保证 T2 在 T1 执行完后执行，T3 在 T2 执行完后执行？
 *
 * 问：join方法的作用？
 *
 * 答：　Thread类中的join方法的主要作用就是同步，它可以使得线程之间的并行执行变为串行执行。
 * 当我们调用某个线程的这个方法时，这个方法会挂起调用线程，直到被调用线程结束执行，调用线程才会继续执行。
 *
 * 问：join方法传参和不传参的区别？
 *
 * 答：join方法中如果传入参数，则表示这样的意思：
 * 如果A线程中掉用B线程的join(10)，则表示A线程会等待B线程执行10毫秒，10毫秒过后，A、B线程并行执行。
 * 需要注意的是，jdk规定，join(0)的意思不是A线程等待B线程0秒，而是A线程等待B线程无限时间，直到B线程执行完毕，
 * 即join(0)等价于join()。
 * @version: 1.0
 */
public class ThreadJoinTest {

    public static void main(String[] args) throws InterruptedException {
        MyThread t1 = new MyThread("T1");
        MyThread t2 = new MyThread("T2");
        MyThread t3 = new MyThread("T3");

        t1.start();
        t1.join();
        t2.start();
        t2.join();
        t3.start();
        t3.join();
    }

}

class MyThread extends Thread {

    public MyThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(this.getName() + ":" + i);
        }
    }
}

/*
T1:0
T1:1
T1:2
T1:3
T1:4
T2:0
T2:1
T2:2
T2:3
T2:4
T3:0
T3:1
T3:2
T3:3
T3:4
 */