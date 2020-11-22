package org.qifei;

import org.junit.Test;
//线程交替按顺序打印0123
public class PrintOneByOne {
    Object o=new Object();
    Boolean f=true;
    class Thread1 extends Thread{
        public void run() {
            synchronized (o){
                for(int i=0;i<20;i++){
                    System.out.println(i * 2);
                    o.notify();
                    if(f){
                        f=false;
                        try {
                            o.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
    class Thread2 extends Thread{
        public void run() {
            synchronized (o){
                for(int i=0;i<20;i++){
                    System.out.println((i * 2)+ 1);
                    o.notify();
                    if(!f){
                        f=true;
                        try {
                            o.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    @Test
    public void test() {
        Thread a = new Thread1();
        Thread b = new Thread2();
        a.start();
        b.start();


    }
}
