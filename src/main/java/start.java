import Database.Services.SessionService;
import Application.Controllers.MainController;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: Aug 17, 2016
 * Time: 11:15:10 AM
 */
public class start {

    /**
     * Main function for application
     *
     * @param args arguments when the program starts
     */
    public static void main(String[] args) {
        MainController.launch(args);
        SessionService.close();
    }
}
