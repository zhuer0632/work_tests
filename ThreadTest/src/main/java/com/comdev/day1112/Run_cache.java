package com.comdev.day1112;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * User: zhu
 * Date: 13-3-31
 * Time: 上午1:04
 */
public class Run_cache
{

    public static void main(String[] args)
    {
        Run_cache t = new Run_cache();
        t.test01();
    }

    /**
     * 永远不会看到    准备写入的下一行出现  读取成功
     */
    private void test01()
    {
        for (int i=0;i<10;i++)
        {
            new  Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    getData("123");
                }
            }).start();
        }

    }


    ReadWriteLock lock = new ReentrantReadWriteLock();

    Map<String, String> data = new HashMap<String, String>();

    /**
     * 从缓存系统中读取数据 [可以实现读取的并发]
     *
     * @return
     */
    public Object getData(String key)
    {
        //先读取缓存，缓存中不存在就读取数据库
        lock.readLock().lock();
        System.out.println(Thread.currentThread().getName()+"==准备读");
        String result ="kong" ;
        if (data.get(key) == null)
        {

            lock.readLock().unlock();//禁止读
            System.out.println(Thread.currentThread().getName()+"==准备写");
            lock.writeLock().lock();//准备写
            result = String.valueOf(new Random().nextInt(10));//从数据库中读取
            if (data.get(key) == null)//写之前进行再次检查  防止其它线程干扰  [双重检查下面的这句只能是一句话，用于存数据的]
            data.put(key, result);
            System.out.println(Thread.currentThread().getName()+"==写入成功"+result);
            lock.writeLock().unlock(); //写完毕，关掉写锁，打开读锁
            lock.readLock().lock();// 只要记得  lock和unlock是永远对称存在的， 读之前一定要  readlock   ，写之前一定要  readunclok 和writelock
        }
        else
        {
            result = data.get(key);
            System.out.println(Thread.currentThread().getName()+"==读取成功"+result);
            //如果不为空直接返回就行了
            lock.readLock().unlock();//这种写法不好，最好try catch一下  把read  unlock写在finnaly中
            return   result;
        }

        lock.readLock().unlock();
        System.out.println(Thread.currentThread().getName()+"取值成功"+result);
        return result;
    }


}
