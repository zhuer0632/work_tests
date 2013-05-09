
package jettyStart;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.server.nio.SelectChannelConnector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class App
{

    public static void main(String[] args) throws Exception
    {

        Server server = new Server();

        SelectChannelConnector connector = new SelectChannelConnector();
        connector.setPort(8080);
        server.addConnector(connector);
        server.setStopAtShutdown(true);

        Handler handler = new AbstractHandler()
        {

            @Override
            public void handle(String target, Request request,
                               HttpServletRequest servletRequest,
                               HttpServletResponse servletResponse) throws IOException,
                    ServletException
            {
                servletResponse.setContentType("text/html");
                servletResponse.setStatus(HttpServletResponse.SC_OK);
                servletResponse.getWriter().println("Hello");
                request.setHandled(true);
            }
        };

        server.setHandler(handler);

        server.start();

    }

}