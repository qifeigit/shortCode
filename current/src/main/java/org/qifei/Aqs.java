package org.qifei;

import org.apache.logging.log4j.*;
import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Aqs {
    Logger log = LogManager.getLogger();

    @Test
    public void test() {
        log.error("2");

    }
}

class MyLock implements Lock {

    class MySync extends AbstractQueuedSynchronizer{

        @Override
        protected boolean tryAcquire(int arg) {
           if(compareAndSetState(0,1)){
               setExclusiveOwnerThread(Thread.currentThread());
               return true;
           }
           return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            return super.tryRelease(arg);
        }

        @Override
        protected boolean isHeldExclusively() {
            return super.isHeldExclusively();
        }
        public Condition newCondition(){
            return new ConditionObject();
        }
    }

    private MySync mySync = new MySync();


    public void lock() {

    }

    //加锁，可打断
    public void lockInterruptibly() throws InterruptedException {

    }

    //只尝试一次加锁
    public boolean tryLock() {
        return false;
    }


    //尝试加锁，带超时时间
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    //解锁
    public void unlock() {

    }

    //条件变量
    public Condition newCondition() {
        return null;
    }
}