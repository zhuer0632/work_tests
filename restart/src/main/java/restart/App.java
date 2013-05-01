package restart;

import com.me.ut.string.StringUT;
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
//            String cronpath = StringUT.root() + File.separator + "sys.properties";
            String cronpath = "bin/sys.properties";
            String debug = PropertiesUT.getProV(cronpath, "debug");
            String cron_debug = PropertiesUT.getProV(cronpath, "cron_debug");
            String cron = PropertiesUT.getProV(cronpath, "cron");
            String cronsqlserver = PropertiesUT.getProV(cronpath, "cronsqlserver");


            if (debug.equals("1"))
            {
                cron = cron_debug;
                cronsqlserver=cron_debug;
            }

            logger.debug(cron);

            JobDetail jobDetail1 = new JobDetail("job1", "jgroup1", RestartJob.class);
            JobDetail jobDetail2 = new JobDetail("job2", "jgroup1", RestartJobSqlServer.class);

            Trigger trigger1 = new CronTrigger("trigger1", "tGroup1", cron);
            Trigger trigger2 = new CronTrigger("trigger2", "tGroup1", cronsqlserver);

            SchedulerFactory sf = new StdSchedulerFactory();
            Scheduler sched = sf.getScheduler();

            sched.scheduleJob(jobDetail1, trigger1);
            sched.scheduleJob(jobDetail2, trigger2);
            sched.start();

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
