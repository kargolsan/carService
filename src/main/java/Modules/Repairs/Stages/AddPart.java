package Modules.Repairs.Stages;

import Application.Interfaces.IControllerDialog;
import Application.Services.LanguageService;
import Application.Classes.StageDialog;
import Application.Stages.Main;
import Modules.Repairs.Models.Part;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;

import java.util.ResourceBundle;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: 18.08.2016
 * Time: 14:10
 */
public class AddPart {

    /** Path to view of assign car stage */
    private static final String VIEW = "/Modules/Repairs/Resources/Views/Dialogs/AddPartView.fxml";

    /** Path to language of main stage */
    private static final String LANGUAGE = "Modules/Repairs/Resources/Languages/repairs";

    /** Icon of stage */
    private static final String ICON = "/Modules/Repairs/Resources/Assets/Images/Icons/part_20.png";

    /** Set resource bundle */
    private static ResourceBundle resourceBundle = LanguageService.getResourceBundle(LANGUAGE);

    /**
     * Show dialog in application
     *
     * @param options for send data
     * @return part of repair
     */
    public static Part showDialog(Object options) {
        Part result = null;
        try {
            FXMLLoader loader = new FXMLLoader(AddPart.class.getClass().getResource(VIEW), resourceBundle);
            VBox page = loader.load();
            Scene scene = new Scene(page);
            StageDialog stage = new StageDialog();
            stage.setScene(scene);
            stage.setTitle(resourceBundle.getString("tab.repair.parts.add_part.title"));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(Main.getStage());
            stage.getIcons().add(new Image(AddPart.class.getResourceAsStream(ICON)));
            IControllerDialog controller = loader.getController();
            controller.loaded(options);
            stage.showAndWait();
            result = (Part)stage.getResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
