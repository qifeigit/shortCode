package org.qifei;

import org.junit.Test;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class PrintIsoscelesTriangle {
    void IsoscelesTriangle(){
        int row = 4;
        for(int i= 0;i<4;i++){
            for(int j = 0;j<row - i;j++){
                System.out.print(" ");
            }
            for(int k =0;k< 2 * i + 1;k++){
                System.out.print("*");
            }
            System.out.println();
        }

    }
    @Test
    public void test() {
        IsoscelesTriangle();
        AbstractQueuedSynchronizer a ;
        ThreadPoolExecutor a = new ThreadPoolExecutor();
    }
}
