package cn.itcast.test.threadFatory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class MyThread implements Runnable {  

    public static void main(String[] args) {
        /**
         * 使用指定的ThreadFactory产生新的Thread
         */

        /**
         * ThreadFactory的一个简单作用就是可以监视线程的创建过程
         */
        ExecutorService ctp =  Executors.newCachedThreadPool(new ThreadFactory() {  //内部类
            private AtomicInteger count = new AtomicInteger();  
            public Thread newThread(Runnable r) {  //Runnable r在此为new MyThread()
                int c = count.incrementAndGet();  
                System.out.println("create no " + c + " Threads");  
                return new WorkThread(r,count); //产生并返回的新线程
                  
            }  
        });
        /**
         * 将MyThread（）当做一个Runnable（）传入public Thread newThread(Runnable r)中，
         */
        ctp.execute(new MyThread());
       ctp.execute(new MyThread());  
       ctp.execute(new MyThread());  
       ctp.execute(new MyThread());  
       ctp.execute(new MyThread());  
       ctp.execute(new MyThread());  

       ctp.shutdown();
        try {  
            ctp.awaitTermination(1200, TimeUnit.SECONDS);  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
    }  
      
    public void run(){
        System.out.println("complete a task!!!");
    }
}  