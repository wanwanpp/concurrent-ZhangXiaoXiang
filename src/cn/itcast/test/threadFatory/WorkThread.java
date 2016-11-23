package cn.itcast.test.threadFatory;

import java.util.concurrent.atomic.AtomicInteger;
 
public class WorkThread extends Thread {  
 
    private Runnable target;  
    private AtomicInteger counter;  
 
    public WorkThread(Runnable target, AtomicInteger counter) {  
        this.target = target;  
        this.counter = counter;  
    }  
 
    @Override 
    public void run() {  
        try {  
            target.run();//执行MyThread的run方法
        } finally {  
            int c = counter.getAndDecrement();  
            System.out.println("terminate no " + c + " Threads");  
        }  
    }  
} 