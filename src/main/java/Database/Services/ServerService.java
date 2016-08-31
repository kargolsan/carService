package Database.Services;

import org.hsqldb.persist.HsqlProperties;
import org.hsqldb.server.Server;
import org.hsqldb.server.ServerAcl;
import org.hsqldb.server.ServerConstants;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: 30.08.2016
 * Time: 19:18
 */
public class ServerService {

    private static Server server;
    private static Boolean running = false;

    public static Boolean isRunning()
    {
        if(server != null)
            server.checkRunning(running);
        return running;
    }

    public static void run(){
        if(server == null)
        {
            System.out.println("Starting HSQL server...");
            server = new Server();
            try
            {
                server.setProperties(new HsqlProperties(ConfigurationService.buildPropertiesServer()));
                server.start();
                running = true;
            }
            catch(ServerAcl.AclFormatException afe)
            {
                System.out.println("Error starting HSQL server." + afe);
            }
            catch (IOException e)
            {
                System.out.println("Error 2 starting HSQL server." + e);
            }
        }
    }

    public static void stop(){
        if(server != null)
        {
            server.stop();
            running = false;
            // Wait while server shuts down
            while (ServerConstants.SERVER_STATE_SHUTDOWN != server.getState())
            {
                try {
                    Thread.currentThread().sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}
