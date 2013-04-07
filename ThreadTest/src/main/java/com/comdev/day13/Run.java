package com.comdev.day13;

import java.util.Date;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * User: zhu
 * Date: 13-4-1
 * Time: 上午12:18
 */
public class Run
{

    public static void main(String[] args)
    {

        //阻塞队列的实现

        Run run = new Run();


        //比较一下  不用线程   如此 调用   取和放
        Date  t1=new Date();
        run.test01();
        Date  t2=new Date();
        System.out.println(t2.getTime()-t1.getTime());


    }

    /**
     * 实现阻塞队列，一个队列里面    放满了后，放入线程等待。取空之后，取线程全部等待。
     */
    private void test01()
    {

        final BlockQueue blockQueue = new BlockQueue();
        //20个个线程去填充
        for (int i = 0; i < 10; i++)
        {
            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    blockQueue.put();
                }
            }).start();
        }

        //10个个线程去取
        for (int i = 0; i < 10; i++)
        {
            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    blockQueue.get();
                }
            }).start();
        }


    }


}
