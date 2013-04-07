package com.comdev.day10;

import java.util.Random;
import java.util.concurrent.*;

/**
 * User: zhu
 * Date: 13-3-30
 * Time: 上午12:45
 */
public class Run
{

    public static void main(String[] args)
    {
        Run run = new Run();
//        run.test01();
        run.test02();
    }

    /**
     * Callable  放入一个
     */
    private void test01()
    {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future<String> future = executorService.submit(new Callable<String>()
        {
            @Override
            public String call() throws Exception
            {
                doOther();
                return "haha";
            }
        });


        try
        {
            String out = future.get();
            System.out.println("任务执行完毕"); //会总是在这里等待
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        doOther();


    }

    private void sleep(int s)
    {
        try
        {
            Thread.sleep(1000*(s+1));
        } catch (InterruptedException e)
        {

        }


    }

    private void doOther()
    {
        System.out.println("正在处理其他事情");
        try
        {
            Thread.sleep(1000);
        } catch (InterruptedException e)
        {

        }


    }

    private void test02()
    {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        CompletionService<String> completionService = new ExecutorCompletionService(executorService);

        //封装10个任务
        for (int i = 0; i < 10; i++)
        {

            final  int taskId=i;
            completionService.submit(new Callable<String>()
            {
                @Override
                public String call() throws Exception
                {
                    sleep(new Random().nextInt(10));
                    return "i="+taskId;  //To change body of implemented methods use File | Settings | File Templates.
                }
            });
        }


        //取回返回结果
        for (int i = 0; i < 10; i++)
        {
            try
            {
                Future<String> f=completionService.take();
                String  result=Thread.currentThread().getName()+"=="+f.get();
                System.out.println(result);
            } catch (Exception e)
            {

            }
        }

        //前面的for循环会阻塞等待所有任务的完成
        System.out.println("所有任务执行完毕");

    }


}
