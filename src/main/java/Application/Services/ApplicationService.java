package Application.Services;

import Database.Services.ServerService;
import javafx.application.Platform;
import Database.Services.SessionService;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: 26.08.2016
 * Time: 16:27
 */
public class ApplicationService {

    /**
     * Exit application
     */
    public static void closeServices(){
        SessionService.close();
        ServerService.stop();
    }

    /**
     * Terminate application
     */
    public static void exit(){
        closeServices();
        Platform.exit();
        System.exit(0);
    }
}
