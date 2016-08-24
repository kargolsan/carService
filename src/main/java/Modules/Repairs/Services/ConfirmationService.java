package Modules.Repairs.Services;

import java.util.Optional;
import java.util.ResourceBundle;

import Application.Services.AlertService;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: 20.08.2016
 * Time: 08:38
 */
public class ConfirmationService {

    /** Path to icon trash */
    private static final String ICON_TRASH = "/Modules/Repairs/Resources/Assets/Images/Icons/trash_20.png";

    /**
     * Confirm delete the repair
     *
     * @return true if can, false if can not
     */
    public static Boolean confirmDelete(ResourceBundle resourceBundle){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(resourceBundle.getString("list_repairs_controller.confirm.confirmation"));
        alert.setHeaderText(null);
        alert.setContentText(resourceBundle.getString("list_repairs_controller.confirm.are_you_sure_delete_repair"));
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(AlertService.class.getResourceAsStream(ICON_TRASH)));
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            return true;
        } else {
            return false;
        }
    }
}
