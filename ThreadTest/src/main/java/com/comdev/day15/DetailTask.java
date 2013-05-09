package com.comdev.day15;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * User: zhu
 * Date: 13-4-7
 * Time: 上午12:03
 */
public class DetailTask implements Runnable
{

    private CyclicBarrier cyclicBarrier;

    public DetailTask(CyclicBarrier cyclicBarrier)
    {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run()
    {
        try
        {
            Thread.sleep(new Random().nextInt(10));
        } catch (InterruptedException e)
        {
        }

        System.out.println(Thread.currentThread().getName() + "Runing!!!");
        System.out.println(Thread.currentThread().getName() + "Over!!!");


        try
        {
            System.out.println("正在等待的线程数："+cyclicBarrier.getNumberWaiting());
            cyclicBarrier.await();
        } catch (Exception e)
        {
        }


    }
}
