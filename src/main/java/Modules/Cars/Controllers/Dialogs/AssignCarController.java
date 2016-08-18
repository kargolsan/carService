package Modules.Cars.Controllers.Dialogs;

import Application.Services.StageDialogService;
import Modules.Cars.Models.Car;
import Modules.Cars.Repositories.CarRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: 18.08.2016
 * Time: 12:40
 */
public class AssignCarController implements Initializable {

    @FXML
    private VBox root;

    @FXML
    private TableView<Car> tableCars;

    @FXML
    private TableColumn<Car, Long> id;

    @FXML
    private TableColumn<Car, String> note;

    @FXML
    private TableColumn<Car, String> registrationNumber;

    @FXML
    private TableColumn<Car, Date> createdAt;

    @FXML
    private TableColumn<Car, Date> updatedAt;

    @FXML
    private Button ok;

    /** Observable list with repairs for table in view */
    public static ObservableList<Car> cars;

    /** result of this dialog */
    private Car result = null;

    /**
     * Constructor
     */
    public AssignCarController()
    {
        cars = FXCollections.observableArrayList();
        cars.addAll(CarRepository.getAll());
    }

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        id.setCellValueFactory(new PropertyValueFactory("id"));
        note.setCellValueFactory(new PropertyValueFactory("note"));
        registrationNumber.setCellValueFactory(new PropertyValueFactory("registrationNumber"));
        createdAt.setCellValueFactory(new PropertyValueFactory("createdAt"));
        updatedAt.setCellValueFactory(new PropertyValueFactory("updatedAt"));
        tableCars.setItems(cars);
        tableCars.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                ok.setDisable(false);
            }
        });
    }

    /**
     * Accept and exit dialog with result object
     */
    @FXML
    public void ok() {
        result = tableCars.getSelectionModel().getSelectedItem();
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
    private void close(){
        StageDialogService stage = (StageDialogService) root.getScene().getWindow();
        stage.setResult(result);
        stage.close();
    }
}
