package com.comdev.day1112;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * User: zhu
 * Date: 13-3-31
 * Time: 上午12:27
 */
public class Run
{
    public static void main(String[] args)
    {
        Run run = new Run();
        run.test01();
    }


    private void test01()
    {

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                out("zhushaolong");
            }
        }).start();

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                out("123456");
            }
        }).start();

    }

    Lock lock=new ReentrantLock();
    private void out(String str)
    {
        lock.lock();
        for (int i = 0; i < str.length(); i++)
        {
            System.out.print(str.charAt(i));
        }
        System.out.println();
        lock.unlock();
    }


}
