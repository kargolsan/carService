package Database.Stages;

import Application.Classes.StageDialog;
import Application.Interfaces.IControllerDialog;
import Application.Interfaces.IControllerTab;
import Application.Services.LanguageService;
import Application.Stages.Loader;
import Application.Stages.Main;
import Modules.Repairs.Models.Part;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ResourceBundle;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: 18.08.2016
 * Time: 14:10
 */
public class ChooseSource {

    /** Stage static */
    private static StageDialog stage;

    /** Path to view of stage */
    private static final String VIEW = "/Database/Resources/Views/ChooseSourceView.fxml";

    /** Path to language of main stage */
    private static final String LANGUAGE = "Database/Resources/Languages/database";

    /** Icon of stage */
    private static final String ICON = "/Database/Resources/Assets/Images/Icons/database_20.png";

    /** Set resource bundle */
    private static ResourceBundle resourceBundle = LanguageService.getResourceBundle(LANGUAGE);

    /**
     * Get stage
     *
     * @return stage
     */
    public static StageDialog getStage(){
        return stage;
    }

    /**
     * Show dialog in application
     *
     * @return true if source of database if chose and false if not set
     */
    public static Boolean start() {
        Boolean result = null;
        try {
            FXMLLoader loader = new FXMLLoader(ChooseSource.class.getClass().getResource(VIEW), resourceBundle);
            VBox page = loader.load();
            Scene scene = new Scene(page);
            StageDialog stage = new StageDialog();
            ChooseSource.stage = stage;
            stage.setScene(scene);
            stage.setTitle(resourceBundle.getString("view.choose_source.setting_database"));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(Loader.getStage());
            stage.getIcons().add(new Image(ChooseSource.class.getResourceAsStream(ICON)));
            stage.setResult(false);
            stage.showAndWait();
            result = (Boolean)stage.getResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
