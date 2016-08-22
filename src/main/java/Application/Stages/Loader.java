package Application.Stages;

import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.stage.StageStyle;
import javafx.scene.layout.VBox;
import javafx.application.Preloader;
import Application.Services.LanguageService;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: Aug 17, 2016
 * Time: 11:15:10 AM
 */
public class Loader extends Preloader {

    /**
     * Stage static
     */
    private static Stage stage;

    /** Path to view of main stage */
    private static final String VIEW = "/Application/Resources/Views/LoaderView.fxml";

    /** Path to language of main stage */
    private static final String LANGUAGE = "Application/Resources/Languages/application";

    /** Icon of stage */
    private static final String ICON = "/Application/Resources/Assets/Images/Icons/app.png";

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * @param stage main of application
     */
    public void start(Stage stage) {
        Loader.stage = stage;
        try {
            VBox page = FXMLLoader.load(getClass().getResource(VIEW), LanguageService.getResourceBundle(LANGUAGE));
            Scene scene = new Scene(page);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.getIcons().add(new Image(getClass().getResourceAsStream(ICON)));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Handle stage change notification
     *
     * @param stateChangeNotification
     */
    @Override
    public void handleStateChangeNotification(StateChangeNotification stateChangeNotification) {
        if (stateChangeNotification.getType() == StateChangeNotification.Type.BEFORE_START) {
            //Loader.stage.hide();
        }
    }

    /**
     * Get stage
     *
     * @return stage
     */
    public static Stage getStage() {
        return stage;
    }
}
