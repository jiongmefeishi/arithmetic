package cn.zqtao.thread;

/**
 * @auther: zqtao
 * @description: Synchronized 底层原理
 * @version: 1.0
 */
public class SynchronizedDemo {

    public void method() {
        synchronized (this) {
            System.out.println("synchronized 代码块");
        }
    }
}
