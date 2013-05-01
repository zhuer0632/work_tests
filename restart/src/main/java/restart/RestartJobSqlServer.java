package restart;

import com.me.ut.exe.RunExe;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * Author: gnoloahs
 * Date: 2013-04-19
 * Time: 上午10:16
 */
public class RestartJobSqlServer implements Job
{
    private static final Logger logger = Logger.getLogger(RestartJobSqlServer.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException
    {

        logger.debug("当前时间：" + (new Date()).toLocaleString());
        logger.debug("准备重启sqlserver：" + (new Date()).toLocaleString());

        try{
            RunExe.ec_2("net stop mssqlserver");
            RunExe.ec_2("net start mssqlserver");
        }
        catch (Exception e)
        {
                e.printStackTrace();
        }


        logger.debug("当前时间：" + (new Date()).toLocaleString());


    }
}
