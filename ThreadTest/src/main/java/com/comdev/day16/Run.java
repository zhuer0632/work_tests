package com.comdev.day16;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * User: zhu
 * Date: 13-4-7
 * Time: 上午12:36
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
        //用于统计10个任务是否已经结束
        final CountDownLatch  countDownLatch=new CountDownLatch(10);

        ExecutorService  pool= Executors.newFixedThreadPool(5);

        for (int i=0;i<10;i++)
        {
            final int   flag=i+1;
                 pool.execute(new Runnable()
                 {
                     @Override
                     public void run()
                     {
                         try
                         {
                             System.out.println("正在执行任务"+flag);
                             Thread.sleep(1);
                             System.out.println("执行完毕"+flag);
                         } catch (Exception e)
                         {

                         }
                         finally {
                             countDownLatch.countDown();
                         }
                     }
                 });



        }


        try
        {
            countDownLatch.await();  //一旦count=0，就跳过这段代码
        } catch (InterruptedException e)
        {

        }

        System.out.println("所有任务执行完毕");

    }


}
