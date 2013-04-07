package com.comdev.day050607;

/**
 * User: zhu
 * Date: 13-3-28
 * Time: 上午12:25
 */

import java.util.Date;
import java.util.Random;

/**
 * 演示多个线程之间共享数据会有问题
 * <p/>
 * 多个线程创建了不同的数据对象，线程内部不同类引用该数据，此处是不能同步的，所以运行期间极有可能我创建的数据被别的线程取走了。
 * 没有绑定线程
 */
public class Run_wrongShow
{

    public static void main(String[] args)
    {

        Run_wrongShow run = new Run_wrongShow();
        run.test01();
    }

      Integer data = 0;

    private void test01()
    {
        for (int i = 0; i < 5; i++)
        {
            try
            {
                Thread.sleep(1);
            } catch (InterruptedException e)
            {
            }

            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    Random random = new Random();
                    random.setSeed(new Date().getTime());

                    data = random.nextInt();//多个线程同时对一个数据操作是引起问题的最终的原因，解决思路就是每个人使用自己的数据拷贝，互不影响。。。
                    System.out.println();
                    System.out.println(Thread.currentThread().getName()+"=生成="+data);
                    new Biz().getData(data);
                    new Biz2().getData(data);
                }
            }).start();
        }


    }



class  Biz
{
    public  void getData(int data)
    {
        System.out.println(Thread.currentThread().getName()+"=Biz="+data);
    }
}

    class  Biz2
    {
        public  void getData(int data)
        {
            System.out.println(Thread.currentThread().getName()+"=Biz2="+data);
        }
    }


}

