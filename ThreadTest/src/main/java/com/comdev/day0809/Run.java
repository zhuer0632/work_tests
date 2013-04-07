package com.comdev.day0809;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * User: zhu
 * Date: 13-3-29
 * Time: 上午1:04
 */
public class Run
{

    public static void main(String[] args)
    {

        Run run = new Run();
        run.test02();


    }

    private void test01()
    {
        //创建的线程池有2个线程
        ExecutorService exccutor = Executors.newFixedThreadPool(2);

        //网线程池里放10个任务
        for (int i = 1; i <= 10; i++)
        {
            final int taskId = i;
            exccutor.execute(new Runnable()
            {
                @Override
                public void run()
                {
                    try
                    {
                        Thread.sleep(10);
                    } catch (InterruptedException e)
                    {

                    }
                    System.out.println(Thread.currentThread().getName() + "==" + taskId);
                }
            });
        }

        System.out.println("执行完毕");
        exccutor.shutdown();//当线程执行完毕之后就执行这句了     异步执行的
        System.out.println("执行完毕2");

    }

    private void test02()
    {
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 1; i <= 10; i++)
        {
            final  int taskId = i;
            executorService.execute(new Runnable()
            {
                @Override
                public void run()
                {
                    try
                    {
                        Thread.sleep(10);
                    } catch (InterruptedException e)
                    {

                    }
                    System.out.println(Thread.currentThread().getName() + "==" + taskId);
                }
            });
        }

        executorService.shutdown();


    }

    private void test03()
    {

    }
}
