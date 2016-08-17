package Application.Controllers;

import Application.Stages.Main;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: Aug 17, 2016
 * Time: 11:15:10 AM
 */
public class MainController {

    /**
     * Launch main stage
     */
    public static void launch(String[] args){
        Main.launch(Main.class, args);
    }
}
