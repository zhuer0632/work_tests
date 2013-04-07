package com.comdev.day1112;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * User: zhu
 * Date: 13-3-31
 * Time: 上午12:35
 */
public class Run_readwriteLock
{

    public static void main(String[] args)
    {
        Run_readwriteLock readwriteLock = new Run_readwriteLock();
        readwriteLock.test01();
    }


    String data = "";

    /**
     * 使用读写锁的好处就是，读的时候可以并发
     */
    private void test01()
    {

        //三个线程去读
        for (int i = 0; i < 3; i++)
        {
            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    try
                    {
                        Thread.sleep(new Random().nextInt(5));
                    } catch (InterruptedException e)
                    {

                    }
                    read();
                }
            }).start();
        }


        //三个线程去写
        for (int i = 0; i < 3; i++)
        {
            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    try
                    {
                        Thread.sleep(new Random().nextInt(5));
                    } catch (InterruptedException e)
                    {

                    }
                    write();
                }
            }).start();
        }


    }

    ReadWriteLock locker = new ReentrantReadWriteLock();

    private void read()
    {
        locker.readLock().lock();
        System.out.println(Thread.currentThread().getName()+"准备读数据");
        System.out.println(Thread.currentThread().getName() + "读取到数据:" + data);
        locker.readLock().unlock();
    }

    private void write()
    {
        locker.writeLock().lock();
        System.out.println(Thread.currentThread().getName()+"准备写数据");
        data = String.valueOf(new Random().nextInt(10));
        System.out.println(Thread.currentThread().getName() + "写入数据" + data);

        locker.writeLock().unlock();
    }

}
