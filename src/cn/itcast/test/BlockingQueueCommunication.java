package cn.itcast.test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


/**
 * 本程序效果，按first，second，third的顺序一次打印，每组first打印2次，second打印5次，third打印10次
 * 每组必须打印完整，不能存在中间被打断的情况。
 */
public class BlockingQueueCommunication {

    public static void main(String[] args) {



        final Business business = new Business();

            new Thread(
                    new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 1; i <= 50; i++) {
                                business.first(i);
                            }
                        }
                    }
            ).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 1; i <= 50; i++) {
                        business.second(i);
                    }
                }
            }).start();
            for (int i = 1; i <= 50; i++) {
                business.third(i);
            }

    }

    static class Business {

        /**
         * 使用阻塞队列在这里起到锁的作用
         * 阻塞队列的长度应该为 1.
         */
        BlockingQueue<Integer> queue1 = new ArrayBlockingQueue<Integer>(1);
        BlockingQueue<Integer> queue2 = new ArrayBlockingQueue<Integer>(1);
        BlockingQueue<Integer> queue3 = new ArrayBlockingQueue<Integer>(1);

        {
//			  Collections.synchronizedMap(null);
            try {
                System.out.println("xxxxxdfsdsafdsa");
                queue3.put(1);
                queue2.put(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void first(int i) {
            try {
                queue1.put(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int j = 1; j <= 2; j++) {
                System.out.println("first thread sequece of " + j + ",loop of " + i);
            }
            try {
                queue2.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void second(int i) {
            try {
                queue2.put(1);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            for (int j = 1; j <= 5; j++) {
                System.out.println("second thread sequece of " + j + ",loop of " + i);
            }
            try {
                queue3.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void third(int i) {

            try {
                queue3.put(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int j = 1; j<=10;j++){
                System.out.println("third thread sequece of " + j + ",loop of " + i);
            }
            try {
                queue1.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}
