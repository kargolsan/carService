package Application.Services;

import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: 19.08.2016
 * Time: 18:24
 */
public class AlertService {

    /** Path to language of main stage */
    private static final String LANGUAGE = "Application/Resources/Languages/application";

    /** Set resource bundle */
    private static ResourceBundle resourceBundle = LanguageService.getResourceBundle(LANGUAGE);

    /**
     * Show alert dialog

     * @param content dor alert
     * @param iconPath for alert
     */
    public static void warning(String content, String iconPath){
        Alert alert = new javafx.scene.control.Alert(Alert.AlertType.WARNING);
        alert.setTitle(resourceBundle.getString("alert.warning"));
        alert.setHeaderText(null);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(AlertService.class.getResourceAsStream(iconPath)));
        alert.setContentText(content);
        alert.showAndWait();
    }
}
