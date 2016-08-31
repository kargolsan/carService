package Application.Stages;

import Application.Classes.Async;
import Application.Services.AlertService;
import Application.Services.ApplicationService;
import Application.Services.PropertiesService;
import Database.Controllers.DatabaseController;
import Database.Services.ConfigurationService;
import Database.Services.SessionService;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.application.Application;
import Application.Services.LanguageService;

import java.net.UnknownHostException;
import java.sql.SQLTransientConnectionException;
import java.util.ResourceBundle;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: Aug 17, 2016
 * Time: 11:15:10 AM
 */
public class Main extends Application {

    /** Stage static */
    private static Stage stage;

    /** Access to run stage */
    private static Boolean canRun = false;

    /** Path to view of main stage */
    private static final String VIEW = "/Application/Resources/Views/MainView.fxml";

    /** Path to language of main stage */
    private static final String LANGUAGE = "Application/Resources/Languages/application";

    /** Path to language of database stage */
    private static final String LANGUAGE_DATABASE = "Database/Resources/Languages/database";

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
        ResourceBundle resourcesDatabase = LanguageService.getResourceBundle(LANGUAGE_DATABASE);

        new Async().run(()->{
            if (! DatabaseController.chooseSource()){
                ApplicationService.exit();
            }
            try {
                ConfigurationService.checkConnectionRemote();
            } catch (Exception e){
                String contentAlert = "";
                if (ConfigurationService.hasUnknownHostException(e)){
                    contentAlert = resourcesDatabase.getString("alert.error_host");
                } else {
                    contentAlert = resourcesDatabase.getString("alert.error_connection_database");
                }
                AlertService.errorException(
                        resourcesDatabase.getString("alert.closing_application"),
                        resourcesDatabase.getString("alert.error_database"),
                        contentAlert,
                        e
                );
                ApplicationService.exit();
            }
            SessionService.preload();

        },()->{
            createStage(stage);
        });
    }

    public static void setCanRun(Boolean canRun) {
        Main.canRun = canRun;
    }

    /**
     * Get stage
     *
     * @return stage
     */
    public static Stage getStage(){
        return stage;
    }

    /**
     * Create stage of main
     *
     * @param stage of main
     */
    public void createStage(Stage stage) {
        Main.stage = stage;
        try {
            VBox page = FXMLLoader.load(getClass().getResource(VIEW), LanguageService.getResourceBundle(LANGUAGE));
            Scene scene = new Scene(page);
            stage.setScene(scene);
            stage.setTitle(String.format("%1$s - v.%2$s", PropertiesService.getApplication("title"), PropertiesService.getApplication("version")));
            stage.getIcons().add(new Image(getClass().getResourceAsStream(ICON)));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Stop stage
     */
    @Override
    public void stop(){
        ApplicationService.exit();
    }
}
