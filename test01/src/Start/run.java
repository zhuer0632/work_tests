package Start;

import org.apache.catalina.startup.Bootstrap;

/**
 * Author: gnoloahs
 * Date: 2013-04-19
 * Time: 下午12:57
 */
public class run
{
    Bootstrap bt = new Bootstrap();


    public static void main(String[] args)
    {

        run ts = new run();

        ts.startOrStopTomat("start", args);


    }

    private void startOrStopTomat(String id, String[] args)
    {

        if (id.equals("start"))
        {
            bt.main(args);
        }
        if (id.equals("stop") && bt != null)
        {
            try
            {
                bt.stopServer();
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        } else
        {
            return;
        }
    }


}
