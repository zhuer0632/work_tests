package com.comdev.day01;

/**
 * User: zhu
 * Date: 13-3-23
 * Time: 上午1:13
 */
public class Thread1
{

    public static void main(String[] args)
    {

        Thread1 t = new Thread1();
//        t.test01();
//        t.test02();
        t.test03();

    }

    /**
     * 会运行子类中的run方法，因为下面的写法就是相当于子类和父类都写在一起了
     */
    private void test03()
    {

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                System.out.println("1");
            }
        })
        {
            public void run()
            {
                System.out.println("2");
            }
        }.start();


    }

    /**
     * 实现Runnable接口
     */
    private void test02()
    {

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {

                System.out.println(Thread.currentThread().getName());

            }
        }).start();

    }

    /**
     * 重写了Thread中的run方法
     */
    private void test01()
    {
        new Thread()
        {
            public void run()
            {
                System.out.println(Thread.currentThread().getName());
            }
        }.start();

    }


}
