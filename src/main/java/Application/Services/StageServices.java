package Application.Services;

import javafx.stage.Stage;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: 24.08.2016
 * Time: 18:13
 */
public class StageServices {
    /**
     * Set title dialog of stage by root
     * 
     * @param stage of dialog
     */
    public static void setTitleDialogByRoot(Stage stage, String title){
        stage.setTitle(title);
    }
}
