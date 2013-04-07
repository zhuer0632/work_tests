package com.comdev.day17;

import java.util.Random;
import java.util.concurrent.Exchanger;

/**
 * User: zhu
 * Date: 13-4-8
 * Time: 上午3:46
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
        final Exchanger<String> exchanger = new Exchanger<String>();
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    String str = "t1";
                    Thread.sleep(new Random().nextInt(1000));
                    String str_ = exchanger.exchange(str);
                    System.out.println(Thread.currentThread().getName() + "out:in=="+str+":"+str_);
                } catch (InterruptedException e)
                {

                }

            }
        }).start();


        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    String str = "t2";
                    Thread.sleep(new Random().nextInt(1000));
                    String str_ = exchanger.exchange(str);
                    System.out.println(Thread.currentThread().getName() + "out:in=="+str+":"+str_);
                } catch (InterruptedException e)
                {

                }


            }
        }).start();


        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    String str = "t3";
                    Thread.sleep(new Random().nextInt(1000));
                    String str_ = exchanger.exchange(str);
                    System.out.println(Thread.currentThread().getName() + "out:in=="+str+":"+str_);
                } catch (InterruptedException e)
                {

                }


            }
        }).start();

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    String str = "t4";
                    Thread.sleep(new Random().nextInt(1000));
                    String str_ = exchanger.exchange(str);
                    System.out.println(Thread.currentThread().getName() + "out:in=="+str+":"+str_);
                } catch (InterruptedException e)
                {

                }


            }
        }).start();



        try
        {
            Thread.sleep(1000000);
        } catch (InterruptedException e)
        {

        }
    }

}
