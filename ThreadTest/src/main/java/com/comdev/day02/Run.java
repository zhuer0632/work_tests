package com.comdev.day02;

import org.junit.Test;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * User: zhu
 * Date: 13-3-23
 * Time: 下午10:26
 */
public class Run
{
    public static void main(String[] args)
    {
        Run run = new Run();
//        run.test01();
        run.test02();
    }


    //定时器简单示例
    public void test01()
    {

        new Timer().schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                System.out.println("task:" + new Date());
            }
        }, 1000, 2000);//一秒后运行一次，然后每隔2秒执行一次

    }

    //实现定时器的间隔爆炸
    public void test02()
    {

        //先调用A任务，A任务中调用B任务 B中还会调用A
        new  Timer().schedule(new SimpleTimerTaskA(),1000); //1秒后执行A

    }


}


class SimpleTimerTaskB extends TimerTask
{
    @Override
    public void run()
    {
        System.out.println("B:"+new Date().getSeconds());
        new  Timer().schedule(new SimpleTimerTaskA(), 2000); //2秒后执行A
    }
}

class SimpleTimerTaskA extends TimerTask
{
    @Override
    public void run()
    {
        System.out.println("A:"+new Date().getSeconds());
        new  Timer().schedule(new SimpleTimerTaskB(), 1000); //1秒后执行B
    }
}
