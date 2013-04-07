package com.comdev.day04;

/**
 * User: zhu
 * Date: 13-3-26
 * Time: 下午11:36
 */
public class Run
{


    public static void main(String[] args)
    {

        final Business business = new Business();

        //创建两个线程去调用m1和m2方法

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                for (int i = 0; i < 50; i++)
                {
                    business.m1();
                }
            }
        }).start();

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                for (int i = 0; i < 50; i++)
                {
                    business.m2();
                }
            }
        }).start();

    }


}


/**
 * 两个互斥方法之间的协调运行
 */
class Business
{

    private boolean shouldM1 = false;

    public synchronized void m1()
    {
        //不是自己运行  那么就等待  让别人运行
        while (!shouldM1)//可能有假唤醒的情况，不是m2唤醒的，而是自己发神经被唤醒了
        {
            try
            {
                this.wait();
            } catch (Exception e)
            {
            }
        }
        System.out.println("M1 doing");
        shouldM1 = false;
        this.notify();

    }

    public synchronized void m2()
    {
        while (shouldM1)
        {
            try
            {
                this.wait();
            } catch (InterruptedException e)
            {

            }
        }

        System.out.println("M2 doing");
        shouldM1 = true;
        this.notify();

    }


}
