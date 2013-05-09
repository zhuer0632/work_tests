package com.comdev.day03;

/**
 * User: zhu
 * Date: 13-3-26
 * Time: 上午2:05
 */
public class Run
{

    //多写个小知识点
    //main方法中不能new静态方法所在的类中的内部类
    //因为内部类是可以访问外部类的属性的，访问属性的前提是对象已经存在了，但是main方法还没有new对象呢
    public static void main(String[] args)
    {
        Run run = new Run();
        run.init();
    }

    private void init()
    {
        new printC().task01();
        new printC().task02();
    }

    static class printC
    {

        private void task01()
        {
            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    while (true)
                    {
                        try
                        {
                            Thread.sleep(10);
                        } catch (InterruptedException e)
                        {
                        }
                        p("123456");
                    }
                }
            }).start();
        }

        private void task02()
        {
            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    while (true)
                    {
                        try
                        {
                            Thread.sleep(10);
                        } catch (InterruptedException e)
                        {
                        }
                        p("abcdef");
                    }
                }
            }).start();
        }

        private  static  synchronized void p(String str)
        {
            int len = str.length();
            for (int i = 0; i < len; i++)
            {
                System.out.print(str.charAt(i));
            }
            System.out.print("\r\n");
        }


    }


}
