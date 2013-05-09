package com.comdev.day14;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * User: zhu
 * Date: 13-4-3
 * Time: 上午12:46
 */
public class Run
{


    public static void main(String[] args)
    {

        Run run = new Run();
        run.test01();

    }

    /**
     * 同时开启好几个线程，一并执行某个任务，但是可以看到同时最多只有指定数量的线程在执行
     */
    private void test01()
    {


       final Semaphore  semaphore=new Semaphore(3);
        ExecutorService  pool= Executors.newCachedThreadPool();//创建变线程数的线程池

        for (int i=0;i<10;i++)
        {
            pool.execute(new Runnable()
            {
                @Override
                public void run()
                {
                    try
                    {
                        System.out.println(Thread.currentThread().getName()+"==正在并发执行的线程数"+(3-semaphore.availablePermits()));
                        semaphore.acquire();
                    } catch (Exception e)
                    {
                    }

                    try
                    {
                        Thread.sleep(10);
                    } catch (InterruptedException e)
                    {

                    }

                    try
                    {
                        semaphore.release();
                        System.out.println(Thread.currentThread().getName()+"==正在并发执行的线程数"+(3-semaphore.availablePermits()));
                    } catch (Exception e)
                    {
                    }



                }
            });
        }




    }


}
