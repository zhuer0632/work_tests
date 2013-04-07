package com.comdev.day13;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * User: zhu
 * Date: 13-4-1
 * Time: 上午12:32
 */
public class BlockQueue
{


    //其实已经有了实现这种功能的类   java.util.concurrent.ArrayBlockingQueue

    Lock lock = new ReentrantLock();

    Condition conditionput = lock.newCondition();
    Condition conditionget = lock.newCondition();

    private  String  type="get";
    public void get()
    {
        lock.lock();
        try
        {
            while(!type.equals("get"))
            {
                System.out.println(Thread.currentThread().getName()+"--get等待");
                conditionget.await();
            }
            //从队列里拿走数据;关于队列 这里就不再用代码描述
            System.out.println(Thread.currentThread().getName()+"--get走了数据");
            type="put";//通知放数据的线程
            System.out.println(Thread.currentThread().getName()+"--put被唤醒");
            conditionput.signal();//通知放数据的线程
        } catch (Exception e)
        {
        } finally
        {
            lock.unlock();
        }
    }

    public void put()
    {
        lock.lock();
        try
        {
            while(!type.equals("put"))
            {
                System.out.println(Thread.currentThread().getName()+"--put等待");
                conditionput.await();
            }
            //从队列里拿走数据;关于队列 这里就不再用代码描述
            System.out.println(Thread.currentThread().getName()+"--put放入了数据");
            System.out.println(Thread.currentThread().getName()+"--get被唤醒");
            type="get";//通知放数据的线程
            conditionget.signal();//通知放数据的线程
        } catch (Exception e)
        {
        } finally
        {
            lock.unlock();
        }
    }


}
