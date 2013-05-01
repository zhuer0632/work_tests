package restart;

import com.me.ut.exe.RunExe;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Author: gnoloahs
 * Date: 2013-04-19
 * Time: 上午10:16
 */
public class RestartJob implements Job
{
    private static final Logger logger = Logger.getLogger(RestartJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException
    {

//        String path = StringUT.root() + "bin/stop.bat";
//        try
//        {
//            RunExe.ec(path);
//        } catch (InterruptedException e)
//        {
//            e.printStackTrace();
//        }
//
//        try
//        {
//            System.out.println("正在等待处理完毕");
//            Thread.sleep(10000)   ;
//        } catch (InterruptedException e)
//        {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }


        try
        {
            Thread t = new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    try
                    {
                        RunExe.ec("bin/stop.bat");
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }
            });

            t.start();


            int i = 0;

            while (true)
            {
                i++;
                System.out.println("等待关闭成功：" + i);
                Thread.sleep(1000);

                if (i == 10)
                {
                    break;
                }
            }


            RunExe.ec("bin/start.bat");
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }

    }
}
