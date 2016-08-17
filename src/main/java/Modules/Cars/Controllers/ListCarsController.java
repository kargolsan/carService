package Modules.Cars.Controllers;

import Modules.Cars.Models.Car;
import Modules.Cars.Repositories.CarRepository;
import Modules.Repairs.Models.Repair;
import Modules.Repairs.Repositories.RepairRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
    private TableColumn<Car, String> note;

    @FXML
    private TableColumn<Car, String> registrationNumber;

    @FXML
    private TableColumn<Car, Date> createdAt;

    @FXML
    private TableColumn<Car, Date> updatedAt;

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
        note.setCellValueFactory(new PropertyValueFactory("note"));
        registrationNumber.setCellValueFactory(new PropertyValueFactory("registrationNumber"));
        createdAt.setCellValueFactory(new PropertyValueFactory("createdAt"));
        updatedAt.setCellValueFactory(new PropertyValueFactory("updatedAt"));
        tableCars.setItems(cars);
    }
}
