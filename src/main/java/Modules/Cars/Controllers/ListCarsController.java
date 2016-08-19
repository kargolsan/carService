package Modules.Cars.Controllers;

import Application.Services.AlertService;
import Application.Services.LanguageService;
import Application.Services.TabsService;
import Modules.Cars.Models.Car;
import Modules.Cars.Repositories.CarRepository;
import Modules.Repairs.Models.Repair;
import Modules.Repairs.Repositories.RepairRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.WindowEvent;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;


/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: 17.08.2016
 * Time: 15:43
 */
public class ListCarsController implements Initializable {

    @FXML
    private TableView<Car> tableCars;

    @FXML
    private TableColumn<Car, Long> id;

    @FXML
    private TableColumn<Car, String> vin;

    @FXML
    private TableColumn<Car, String> note;

    @FXML
    private TableColumn<Car, String> registrationNumber;

    @FXML
    private TableColumn<Car, Date> createdAt;

    @FXML
    private TableColumn<Car, Date> updatedAt;

    @FXML
    private MenuItem delete;

    /** Path to language of main stage */
    private static final String LANGUAGE = "Modules/Cars/Resources/Languages/cars";

    /** Set resource bundle */
    private ResourceBundle resourceBundle = LanguageService.getResourceBundle(LANGUAGE);

    /** Observable list with repairs for table in view */
    public static ObservableList<Car> cars;

    /**
     * Constructor
     */
    public ListCarsController()
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
        vin.setCellValueFactory(new PropertyValueFactory("vin"));
        note.setCellValueFactory(new PropertyValueFactory("note"));
        registrationNumber.setCellValueFactory(new PropertyValueFactory("registrationNumber"));
        createdAt.setCellValueFactory(new PropertyValueFactory("createdAt"));
        updatedAt.setCellValueFactory(new PropertyValueFactory("updatedAt"));
        tableCars.setItems(cars);
    }

    @FXML
    public void refresh(){
        cars.clear();
        cars.addAll(CarRepository.getAll());
    }

    @FXML
    public void add(){
        TabsService.addTab("/Modules/Cars/Resources/Views/Tabs/AddCarView.fxml", "Modules/Cars/Resources/Languages/cars");
    }

    /**
     * Showing menu context
     *
     * @param e
     */
    @FXML
    protected void showingContextMenu(WindowEvent e) {
        Car car = tableCars.getSelectionModel().getSelectedItem();
        delete.setVisible(car != null);
    }

    /**
     * Delete car
     */
    @FXML
    private void delete()
    {
        Car car = tableCars.getSelectionModel().getSelectedItem();
        Repair carCanDelete = RepairRepository.carCanDelete(car.getId());
        Boolean result = false;
        if (carCanDelete == null){
            result = CarRepository.delete(car.getId());
        } else {
            AlertService.warning(
                    resourceBundle.getString("list_car_controller.can_not_delete"),
                    String.format(resourceBundle.getString("list_car_controller.can_not_delete_because_car_assign_to_repair"), carCanDelete.getId())
            );
        }
        if (result){
            cars.remove(car);
        }
    }

}
