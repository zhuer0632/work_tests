package com.comdev.day050607;

/**
 * User: zhu
 * Date: 13-3-28
 * Time: 上午12:17
 */
public class Run
{


    public static void main(String[] args)
    {
        Run run = new Run();
        run.test01();
    }

   static   final  ThreadLocal<VOPerson> data = new ThreadLocal<VOPerson>();

    private void test01()
    {


        for (int i=0;i<5;i++)
        {
            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    System.out.println();

                    VOPerson vo = new VOPerson();
                    System.out.println(Thread.currentThread().getName()+"=生成="+vo.toString());
                    data.set(vo);



                    new Biz().getData();

                    try
                    {
                        Thread.sleep(1000);
                    } catch (InterruptedException e)
                    {

                    }

                    new Biz2().getData();
                }
            }).start();
        }


    }

}

class   Biz{

    public  void  getData()
    {
        System.out.println(Thread.currentThread().getName() + "=biz=" + Run.data.get());
    }
}
class   Biz2{

    public  void  getData()
    {
        System.out.println(Thread.currentThread().getName()+"=biz2="+Run.data.get());
    }
}
