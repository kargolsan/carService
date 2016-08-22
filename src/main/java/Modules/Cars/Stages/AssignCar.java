package Modules.Cars.Stages;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.fxml.FXMLLoader;
import Modules.Cars.Models.Car;
import Application.Stages.Main;
import javafx.scene.layout.VBox;
import Application.Services.LanguageService;
import Application.Services.StageDialogService;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: 18.08.2016
 * Time: 14:10
 */
public class AssignCar {

    /** Path to view of assign car stage */
    private static final String VIEW = "/Modules/Cars/Resources/Views/Dialogs/AssignCarView.fxml";

    /** Path to language of main stage */
    private static final String LANGUAGE = "Modules/Cars/Resources/Languages/cars";

    /** Icon of stage */
    private static final String ICON = "/Application/Resources/Assets/Images/Icons/app.png";

    /**
     * Show dialog in application
     *
     * @return Car
     */
    public static Car showDialog() {
        Car result = null;
        try {
            VBox page = FXMLLoader.load(AssignCar.class.getClass().getResource(VIEW), LanguageService.getResourceBundle(LANGUAGE));
            Scene scene = new Scene(page);
            StageDialogService stage = new StageDialogService();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(Main.getStage());
            stage.getIcons().add(new Image(AssignCar.class.getResourceAsStream(ICON)));
            stage.showAndWait();
            result = (Car)stage.getResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
