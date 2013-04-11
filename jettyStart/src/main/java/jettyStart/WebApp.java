
package jettyStart;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class WebApp
{

    public static void main(String[] args) throws Exception
    {
        //startWar();
        startWebApp();
    }

    private static void startWebApp() throws Exception
    {
        Server server = new Server(8080);
        WebAppContext context = new WebAppContext();
        context.setContextPath("/saas");
        context.setDescriptor("c:/saas/WEB-INF/web.xml");
        context.setResourceBase("c:/saas");
        context.setParentLoaderPriority(true);
        server.setHandler(context);
        server.start();
        server.join();
    }


    public static void startWar() throws Exception
    {
        Server server = new Server(8080);

        WebAppContext context = new WebAppContext();
        context.setContextPath("/saas");
        context.setWar("C:\\saas.war");
        server.setHandler(context);

        server.start();
        server.join();

    }


}