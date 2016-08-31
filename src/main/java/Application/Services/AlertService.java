package Application.Services;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: 19.08.2016
 * Time: 18:24
 */
public class AlertService {

    /**
     * Icon for alert error
     */
    private static final String ICON_ERROR = "/Application/Resources/Assets/Images/Icons/error_20.png";

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

    /**
     * Show alert error with details of exception
     *
     * @param title alert
     * @param header alert
     * @param content alert
     * @param ex exception for alert
     */
    public static void errorException(String title, String header, String content, Exception ex){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        if (ex != null){
            ex.printStackTrace(pw);
        }

        String exceptionText = sw.toString();

        Label label = new Label(resourceBundle.getString("alert_service.the_exception_stacktrace_was"));

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

        alert.getDialogPane().setExpandableContent(expContent);

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(AlertService.class.getResourceAsStream(ICON_ERROR)));

        alert.showAndWait();
    }
}
