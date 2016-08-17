package Application.Stages;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.application.Application;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: Aug 17, 2016
 * Time: 11:15:10 AM
 */
public class Main extends Application {

    /** Path to view of main stage */
    private static final String VIEW = "/Application/Resources/Views/MainView.fxml";

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * @param stage main of application
     */
    public void start(Stage stage) {
        try {
            VBox page = FXMLLoader.load(getClass().getResource(VIEW));
            Scene scene = new Scene(page);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
