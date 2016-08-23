package Modules.Cars.Controllers.Dialogs;

import java.net.URL;
import java.util.Date;

import javafx.fxml.FXML;
import Modules.Cars.Models.Car;
import javafx.scene.layout.VBox;

import java.util.ResourceBundle;

import javafx.scene.control.Tab;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Application.Interfaces.IControllerTab;
import Modules.Cars.Repositories.CarRepository;
import Application.Services.StageDialogService;
import Modules.Cars.Services.CellValueFactoryService;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: 18.08.2016
 * Time: 12:40
 */
public class AssignCarController implements Initializable, IControllerTab {

    @FXML
    private VBox root;

    @FXML
    private TableView<Car> tableCars;

    @FXML
    private TableColumn<Car, Long> id;

    @FXML
    private TableColumn<Car, String> note;

    @FXML
    private TableColumn<Car, String> vin;

    @FXML
    private TableColumn<Car, String> registrationNumber;

    @FXML
    private TableColumn<Car, String> user;

    @FXML
    private TableColumn<Car, String> phones;

    @FXML
    private TableColumn<Car, Date> yearProduction;

    @FXML
    private TableColumn<Car, String> body;

    @FXML
    private TableColumn<Car, String> engineCapacity;

    @FXML
    private TableColumn<Car, String> engineModel;

    @FXML
    private TableColumn<Car, String> enginePower;

    @FXML
    private TableColumn<Car, String> fuel;

    @FXML
    private TableColumn<Car, String> manufacturer;

    @FXML
    private TableColumn<Car, String> model;

    @FXML
    private TableColumn<Car, Date> createdAt;

    @FXML
    private TableColumn<Car, Date> updatedAt;

    @FXML
    private Button assign;

    /**
     * Observable list with repairs for table in view
     */
    public static ObservableList<Car> cars;

    /**
     * result of this dialog
     */
    private Car result = null;

    /**
     * Constructor
     */
    public AssignCarController() {
        cars = FXCollections.observableArrayList();
        cars.addAll(CarRepository.sortCreatedAtDesc(CarRepository.getAll()));
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
        vin.setCellValueFactory(new PropertyValueFactory("vin"));
        note.setCellValueFactory(new PropertyValueFactory("note"));
        registrationNumber.setCellValueFactory(new PropertyValueFactory("registrationNumber"));
        user.setCellValueFactory(new PropertyValueFactory("user"));
        phones.setCellValueFactory(new PropertyValueFactory("phones"));
        yearProduction.setCellValueFactory(new PropertyValueFactory("yearProduction"));
        body.setCellValueFactory(new PropertyValueFactory("body"));
        engineCapacity.setCellValueFactory(new PropertyValueFactory("engineCapacity"));
        engineModel.setCellValueFactory(new PropertyValueFactory("engineModel"));
        enginePower.setCellValueFactory(new PropertyValueFactory("enginePower"));
        fuel.setCellValueFactory(new PropertyValueFactory("fuel"));
        manufacturer.setCellValueFactory(new PropertyValueFactory("manufacturer"));
        model.setCellValueFactory(new PropertyValueFactory("model"));
        createdAt.setCellValueFactory(new CellValueFactoryService().propertyCreatedAtFactory());
        updatedAt.setCellValueFactory(new CellValueFactoryService().propertyUpdatedAtFactory());
        tableCars.setItems(cars);
        tableCars.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                assign.setDisable(false);
            }
        });
    }

    /**
     * loaded after initialized controller
     *
     * @param options for controller
     * @param tab     of controller
     * @param lastTab opened
     */
    @Override
    public void loaded(Object options, Tab tab, Tab lastTab) {

    }

    @FXML
    public void refresh() {
        cars.clear();
        cars.addAll(CarRepository.sortCreatedAtDesc(CarRepository.getAll()));
    }


    /**
     * Accept and exit dialog with result object
     */
    @FXML
    public void assign() {
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
    private void close() {
        StageDialogService stage = (StageDialogService) root.getScene().getWindow();
        stage.setResult(result);
        stage.close();
    }
}
