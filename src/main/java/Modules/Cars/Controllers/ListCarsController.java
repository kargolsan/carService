package Modules.Cars.Controllers;

import java.util.*;
import java.net.URL;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import Modules.Cars.Models.Car;
import javafx.stage.WindowEvent;
import javafx.fxml.Initializable;
import Modules.Repairs.Models.Repair;
import Application.Services.TabsService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Application.Services.AlertService;
import Application.Services.LanguageService;
import Application.Interfaces.IControllerTab;
import Modules.Cars.Repositories.CarRepository;
import Modules.Cars.Services.ConfirmationService;
import Modules.Cars.Services.CellValueFactoryService;
import Modules.Repairs.Repositories.RepairRepository;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: 17.08.2016
 * Time: 15:43
 */
public class ListCarsController implements Initializable, IControllerTab {

    /** tab of this controller */
    private Tab tab;

    /** last opened tab */
    private Tab lastTab;

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

    @FXML
    private MenuItem edit;

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
        createdAt.setCellValueFactory(new CellValueFactoryService().propertyCreatedAtFactory());
        updatedAt.setCellValueFactory(new CellValueFactoryService().propertyUpdatedAtFactory());
        tableCars.setItems(cars);
    }

    /**
     * loaded after initialized controller
     *
     * @param options for controller
     * @param tab of controller
     * @param lastTab opened
     */
    @Override
    public void loaded(Object options, Tab tab, Tab lastTab) {
        configurationTab(tab, lastTab);
    }

    /**
     * Refresh cars
     */
    @FXML
    public void refresh(){
        cars.clear();
        cars.addAll(CarRepository.sortCreatedAtDesc(CarRepository.getAll()));
    }

    /**
     * Add car
     */
    @FXML
    public void add(){
        TabsService.addTab("/Modules/Cars/Resources/Views/AddCarView.fxml", "Modules/Cars/Resources/Languages/cars", null);
    }

    /**
     * Edit car
     */
    @FXML
    public void edit(){
        TabsService.addTab(
                "/Modules/Cars/Resources/Views/EditCarView.fxml",
                "Modules/Cars/Resources/Languages/cars",
                tableCars.getSelectionModel().getSelectedItem()
        );
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
        edit.setVisible(car != null);
    }

    /**
     * Delete car
     */
    @FXML
    private void delete()
    {
        if (!ConfirmationService.confirmDelete(resourceBundle)) { return; }
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

    /**
     * Configuration tab
     */
    private void configurationTab(Tab tab, Tab lastTab){
        this.tab = tab;
        this.lastTab = lastTab;
        this.tab.setText(resourceBundle.getString("tab.list_cars.title"));
    }
}
