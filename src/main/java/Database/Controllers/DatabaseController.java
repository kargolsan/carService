package Database.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import Application.Classes.StageDialog;
import Application.Services.AlertService;
import Application.Services.ApplicationService;
import Application.Services.LanguageService;
import Database.Controllers.Options.ClientController;
import Database.Controllers.Options.LocalController;
import Database.Controllers.Options.ServerController;
import Database.Services.*;
import Modules.Repairs.Controllers.Items.PartsController;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import Database.Stages.ChooseSource;
import Database.Models.Database;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: 30.08.2016
 * Time: 14:33
 */
public class DatabaseController implements Initializable {

    @FXML
    private ClientController clientController;

    @FXML
    private LocalController localController;

    @FXML
    private ServerController serverController;

    @FXML
    private VBox containerOptions;

    @FXML
    private ComboBox<Database> comboOptions;

    @FXML
    private Text prompt;

    /** Path to language of main stage */
    private static final String LANGUAGE = "Database/Resources/Languages/database";

    /** Set resource bundle */
    private ResourceBundle resourceBundle = LanguageService.getResourceBundle(LANGUAGE);

    /** Observable list */
    public static ObservableList<Database> options;

    /**
     * Constructor
     */
    public DatabaseController() {
        options = FXCollections.observableArrayList(
                new Database(
                        resourceBundle.getString("controllers.database.option.database_local"),
                        resourceBundle.getString("controllers.database.option.database_local.description"),
                        DatabaseOption.LOCAL
                ),
                new Database(
                        resourceBundle.getString("controllers.database.option.database_server"),
                        resourceBundle.getString("controllers.database.option.database_server.description"),
                        DatabaseOption.SERVER
                ),
                new Database(
                        resourceBundle.getString("controllers.database.option.database_client"),
                        resourceBundle.getString("controllers.database.option.database_client.description"),
                        DatabaseOption.CLIENT
                )
        );
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
        comboOptions.setItems(options);
        comboOptions.valueProperty().addListener(new ChangeListener<Database>() {
            @Override public void changed(ObservableValue ov, Database d1, Database d2) {
                prompt.setText(d2.getDescription());
                switch (d2.getOption()) {
                    case LOCAL:
                        localController.getRoot().getStyleClass().remove("hidden");
                        clientController.getRoot().getStyleClass().add("hidden");
                        serverController.getRoot().getStyleClass().add("hidden");
                        break;

                    case SERVER:
                        serverController.getRoot().getStyleClass().remove("hidden");
                        clientController.getRoot().getStyleClass().add("hidden");
                        localController.getRoot().getStyleClass().add("hidden");
                        break;

                    case CLIENT:
                        clientController.getRoot().getStyleClass().remove("hidden");
                        serverController.getRoot().getStyleClass().add("hidden");
                        localController.getRoot().getStyleClass().add("hidden");
                        break;
                }
                if (ChooseSource.getStage() !=null)
                {
                    ChooseSource.getStage().sizeToScene();
                }
            }
        });
        comboOptions.setConverter(new ConverterService().convertDatabaseToTitle());
        comboOptions.setCellFactory(new CellFactoryService().propertyTitleFactory());
        comboOptions.getSelectionModel().selectFirst();
    }

    /**
     * Choose source of your database:
     * - local
     * - server
     * - client
     * @return true if source of database if chose and false if not set
     */
    public static Boolean chooseSource(){

        return ChooseSource.start();
    }

    /**
     * Confirm choose database
     */
    @FXML
    public void confirm(){
        Database database = comboOptions.getSelectionModel().getSelectedItem();
        if (database.getOption()==DatabaseOption.CLIENT){
            ConfigurationService.setServerIp(clientController.getIp().getText());
        }
        ConfigurationService.setOption(database.getOption());
        StageDialog stage = ChooseSource.getStage();

        stage.setResult(true);

        close();
    }

    /**
     * Exit dialog with null result
     */
    @FXML
    public void cancel() {
        close();
    }

    /**
     * Close stage
     */
    private void close() {
        StageDialog stage = ChooseSource.getStage();
        stage.close();
    }
}
