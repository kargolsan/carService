package Database.Controllers.Options;

import Database.Models.Database;
import Database.Services.CellFactoryService;
import Database.Services.ConfigurationService;
import Database.Services.ConverterService;
import Database.Services.ServerService;
import Database.Stages.ChooseSource;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.InetAddress;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: 30.08.2016
 * Time: 18:16
 */
public class ServerController implements Initializable {

    @FXML
    private TextField localization;

    @FXML
    private VBox root;

    public VBox getRoot() {
        return root;
    }

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    public void initialize(URL location, ResourceBundle resources) {
        localization.setText(ConfigurationService.getLocalizationLocalDatabase());
    }
}

