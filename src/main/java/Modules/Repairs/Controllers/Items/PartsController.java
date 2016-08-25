package Modules.Repairs.Controllers.Items;

import Application.Services.LanguageService;
import Application.Services.TabsService;
import Modules.Cars.Models.Car;
import Modules.Cars.Stages.AssignCar;
import Modules.Repairs.Models.Part;
import Modules.Repairs.Models.Repair;
import Modules.Repairs.Models.Service;
import Modules.Repairs.Repositories.RepairRepository;
import Modules.Repairs.Services.ConfirmationService;
import Modules.Repairs.Services.PricesService;
import Modules.Repairs.Stages.AddPart;
import Modules.Repairs.Stages.AddService;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.WindowEvent;

import java.math.BigDecimal;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: 17.08.2016
 * Time: 15:43
 */
public class PartsController implements Initializable {

    @FXML
    private TableView<Part> tableParts;

    @FXML
    private TableColumn<Part, Long> id;

    @FXML
    private TableColumn<Part, String> name;

    @FXML
    private TableColumn<Part, Double> quantity;

    @FXML
    private TableColumn<Part, BigDecimal> priceWithoutTax;

    @FXML
    private TableColumn<Part, Double> taxPercentage;

    @FXML
    private TableColumn<Part, String> note;

    @FXML
    private TableColumn<Part, Date> createdAt;

    @FXML
    private TableColumn<Part, Date> updatedAt;

    @FXML
    private MenuItem delete;

    @FXML
    private MenuItem edit;

    @FXML
    private Label totalWithoutTax;

    @FXML
    private Label totalTax;

    @FXML
    private Label totalWithTax;

    /** Observable list with repairs for table in view */
    public ObservableList<Part> parts;

    /** Path to language of main stage */
    private static final String LANGUAGE = "Modules/Repairs/Resources/Languages/repairs";

    /** Set resource bundle */
    private ResourceBundle resourceBundle = LanguageService.getResourceBundle(LANGUAGE);

    /**
     * property of total without tax
     */
    public StringProperty propertyTotalWithoutTax = new SimpleStringProperty();

    /**
     * property of total tax
     */
    public StringProperty propertyTotalTax = new SimpleStringProperty();

    /**
     * property of total with tax
     */
    public StringProperty propertyTotalWithTax = new SimpleStringProperty();

    /**
     * Constructor
     */
    public PartsController()
    {
        parts = FXCollections.observableArrayList();
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
        totalWithoutTax.textProperty().bind(propertyTotalWithoutTax);
        totalTax.textProperty().bind(propertyTotalTax);
        totalWithTax.textProperty().bind(propertyTotalWithTax);

        PricesService.calculationListenerPricesParts(parts, propertyTotalWithoutTax, propertyTotalTax, propertyTotalWithTax);

        id.setCellValueFactory(new PropertyValueFactory("id"));
        name.setCellValueFactory(new PropertyValueFactory("name"));
        quantity.setCellValueFactory(new PropertyValueFactory("quantity"));
        priceWithoutTax.setCellValueFactory(new PropertyValueFactory("priceWithoutTax"));
        taxPercentage.setCellValueFactory(new PropertyValueFactory("taxPercentage"));
        note.setCellValueFactory(new PropertyValueFactory("note"));
        createdAt.setCellValueFactory(new PropertyValueFactory("createdAt"));
        updatedAt.setCellValueFactory(new PropertyValueFactory("updatedAt"));
        tableParts.setItems(parts);
    }

    /**
     * Ad item to parts
     */
    @FXML
    public void add(){
        Part part = AddPart.showDialog(null);
        if (part != null){
            parts.add(part);
        }
    }

    /**
     * Edit repair
     */
    @FXML
    public void edit(){
        Part selectPart = tableParts.getSelectionModel().getSelectedItem();
        Part part = AddPart.showDialog(selectPart);
        parts.set(parts.indexOf(selectPart), part);
        Part partInList = parts.stream().filter(p -> p.getId() == part.getId()).findFirst().get();
        parts.set(parts.indexOf(partInList), part);
    }

    /**
     * Delete car
     */
    @FXML
    private void delete()
    {
        if (!ConfirmationService.confirmDeletePart(resourceBundle)) { return; }
        Part part = tableParts.getSelectionModel().getSelectedItem();
        parts.remove(part);
    }

    /**
     * Showing menu context
     *
     * @param e
     */
    @FXML
    protected void showingContextMenu(WindowEvent e) {
        Part part = tableParts.getSelectionModel().getSelectedItem();
        delete.setVisible(part != null);
        edit.setVisible(part != null);
    }
}
