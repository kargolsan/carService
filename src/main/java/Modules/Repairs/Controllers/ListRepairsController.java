package Modules.Repairs.Controllers;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import Application.Services.TabsService;
import Modules.Cars.Models.Car;
import Modules.Cars.Repositories.CarRepository;
import Modules.Repairs.Models.Repair;
import Modules.Repairs.Repositories.RepairRepository;
import Modules.Repairs.Services.CellValueFactoryService;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.WindowEvent;
import javafx.util.Callback;


/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: 17.08.2016
 * Time: 15:43
 */
public class ListRepairsController implements Initializable {

    @FXML
    private TableView<Repair> tableRepairs;

    @FXML
    private TableColumn<Repair, Long> id;

    @FXML
    private TableColumn<Repair, Date> dateStart;

    @FXML
    private TableColumn<Repair, Date> dateEnd;

    @FXML
    private TableColumn<Repair, String> note;

    @FXML
    private TableColumn<Repair, String> carId;

    @FXML
    private TableColumn<Repair, String> carRegistrationNumber;

    @FXML
    private TableColumn<Repair, Date> createdAt;

    @FXML
    private TableColumn<Repair, Date> updatedAt;

    @FXML
    private MenuItem delete;

    /** Observable list with repairs for table in view */
    public static ObservableList<Repair> repairs;

    /**
     * Constructor
     */
    public ListRepairsController()
    {
        repairs = FXCollections.observableArrayList();
        repairs.addAll(RepairRepository.getAll());
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
        dateStart.setCellValueFactory(new PropertyValueFactory("dateStart"));
        dateEnd.setCellValueFactory(new PropertyValueFactory("dateEnd"));
        note.setCellValueFactory(new PropertyValueFactory("note"));
        carId.setCellValueFactory(new PropertyValueFactory("carId"));
        carRegistrationNumber.setCellValueFactory(new CellValueFactoryService().propertyRegistrationNumberFactory());
        createdAt.setCellValueFactory(new CellValueFactoryService().propertyCreatedAtFactory());
        updatedAt.setCellValueFactory(new PropertyValueFactory("updatedAt"));
        tableRepairs.setItems(repairs);
    }

    @FXML
    public void refresh(){
        repairs.clear();
        repairs.addAll(RepairRepository.getAll());
    }

    @FXML
    public void add(){
        TabsService.addTab("/Modules/Repairs/Resources/Views/Tabs/AddRepairView.fxml");
    }

    /**
     * Showing menu context
     *
     * @param e
     */
    @FXML
    protected void showingContextMenu(WindowEvent e) {
        Repair repair = tableRepairs.getSelectionModel().getSelectedItem();
        delete.setVisible(repair != null);
    }

    /**
     * Delete car
     */
    @FXML
    private void delete()
    {
        Repair repair = tableRepairs.getSelectionModel().getSelectedItem();
        Boolean result = RepairRepository.delete(repair.getId());
        if (result){
            repairs.remove(repair);
        }
    }
}
