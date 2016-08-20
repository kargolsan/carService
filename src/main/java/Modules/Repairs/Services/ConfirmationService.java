package Modules.Repairs.Services;

import java.util.Optional;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: 20.08.2016
 * Time: 08:38
 */
public class ConfirmationService {

    /**
     * Confirm delete the repair
     *
     * @return true if can, false if can not
     */
    public static Boolean confirmDelete(ResourceBundle resourceBundle){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(resourceBundle.getString("list_repairs_controller.confirm.confirmation"));
        alert.setHeaderText(resourceBundle.getString("list_repairs_controller.confirm.delete_repair"));
        alert.setContentText(resourceBundle.getString("list_repairs_controller.confirm.are_you_sure_delete_repair"));

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            return true;
        } else {
            return false;
        }
    }
}
