package Application.Services;

import javafx.scene.control.Alert;

import java.util.ResourceBundle;

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
     *
     * @param header for alert
     * @param content dor alert
     */
    public static void warning(String header, String content){
        Alert alert = new javafx.scene.control.Alert(Alert.AlertType.WARNING);
        alert.setTitle(resourceBundle.getString("alert.warning"));
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
