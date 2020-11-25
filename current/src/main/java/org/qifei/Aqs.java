package org.qifei;

import org.apache.logging.log4j.*;
import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;


import static java.lang.Thread.sleep;

public class Aqs {
    Logger log = LogManager.getLogger();

    @Test
    public void test() {
        final MyLock lock = new MyLock();
        new Thread(()->{
            lock.lock();
//            lock.tryLock();
            try {
                log.error("lock");
                sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                log.error("unlock");
            }
        },"t1").start();
        log.error("lock111");
        new Thread(()->{
            lock.tryLock();
            try {
                log.error("lock");
            } finally {
                lock.unlock();
                log.error("unlock");
            }
        },"t2").start();

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
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }
        public Condition newCondition(){
            return new ConditionObject();
        }
    }

    private MySync mySync = new MySync();


    public void lock() {
        mySync.acquire(1);
    }

    //加锁，可打断
    public void lockInterruptibly() throws InterruptedException {
        mySync.acquireInterruptibly(1);
    }

    //只尝试一次加锁
    public boolean tryLock() {
       return mySync.tryAcquire(0);
    }


    //尝试加锁，带超时时间
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    //解锁
    public void unlock() {
        mySync.release(1);
    }

    //条件变量
    public Condition newCondition() {
        return mySync.newCondition();
    }
}