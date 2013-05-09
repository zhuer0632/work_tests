package com.comdev.day15;

import java.util.concurrent.CyclicBarrier;

/**
 * User: zhu
 * Date: 13-4-6
 * Time: 下午11:58
 */
public class Run
{

      public   static  void main(String[]args)
      {

          Run   run=new Run();
          run.test01();


      }

    private void test01()
    {
        CyclicBarrier   cyclicBarrier=new CyclicBarrier(3,new Runnable()
        {
            @Override
            public void run()
            {
                System.out.println("所有线程执行完毕！！！");
            }
        });

        new Thread(new DetailTask(cyclicBarrier)).start();
        new Thread(new DetailTask(cyclicBarrier)).start();
        new Thread(new DetailTask(cyclicBarrier)).start();


        if(cyclicBarrier.getNumberWaiting()==0)
        {
//            System.out.println("所有线程执行完毕");
        }

    }

}
