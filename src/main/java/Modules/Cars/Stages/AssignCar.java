package Modules.Cars.Stages;

import Application.Services.LanguageService;
import Application.Services.StageDialogService;
import Application.Stages.Main;
import Modules.Cars.Models.Car;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;

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
            stage.showAndWait();
            result = (Car)stage.getResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
