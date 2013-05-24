package com.comdev.day01.test;

import java.util.Hashtable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * User: zhu
 * Date: 13-5-12
 * Time: 下午10:31
 */

public class Test implements Runnable
{

    // private static AtomicInteger static_i=new AtomicInteger(0);//静态变量
    public static final Hashtable<String, Integer> daos = new Hashtable<String, Integer>();

    static
    {
        daos.put("1", 0);
    }

    public static void main(String[] args)
    {
        Test t = new Test();
        // 启动尽量多的线程才能很容易的模拟问题

        try
        {
            Thread.sleep(1000);
        } catch (InterruptedException e)
        {

        }
        System.out.println(daos.get("1"));

        for (int i = 0; i < 1000; i++)
        {
            // t可以换成new Test(),保证每个线程都在不同的对象中执行，结果一样
            new Thread(t, "线程" + i).start();
        }
    }

    public void run()
    {
//        synchronized (this)
        //
        {
            // static_i = 4;
            // static_i.set(4);
            daos.put("1", 4);
            System.out.println("[" + Thread.currentThread().getName()
                    + "]获取static_i 的值:" + daos.get("1"));
            daos.put("1", daos.get("1") * 2);
            System.out.println("[" + Thread.currentThread().getName()
                    + "]获取static_i*3的值:" + daos.get("1"));
        }

    }

}