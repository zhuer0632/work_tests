package restart;

import com.me.ut.xml.PropertiesUT;
import org.apache.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.File;

/**
 * Hello world!
 */
public class App
{

    private static final Logger logger = Logger.getLogger(App.class);

    public static void main(String[] args)
    {
        App app = new App();
        app.run();
    }

    private void run()
    {
        try
        {
//            String cronpath = cronpath.root() + File.separator + "sys.properties";
            String cronpath = "bin/sys.properties";
            String debug = PropertiesUT.getProV(cronpath, "debug");
            String cron_debug = PropertiesUT.getProV(cronpath, "cron_debug");
            String cron = PropertiesUT.getProV(cronpath, "cron");


            if (debug.equals("1"))
            {
                cron = cron_debug;
            }

            logger.debug(cron);

            JobDetail jobDetail = new JobDetail("job1", "jgroup1", RestartJob.class);
            Trigger trigger = new CronTrigger("trigger1", "tGroup1", cron);
            SchedulerFactory sf = new StdSchedulerFactory();
            Scheduler sched = sf.getScheduler();
            sched.scheduleJob(jobDetail, trigger);
            sched.start();

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
