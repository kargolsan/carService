package Modules.Repairs.Controllers.Items;


import Application.Services.LanguageService;
import Modules.Repairs.Controllers.ListRepairsController;
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
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
public class ServicesController implements Initializable {

    @FXML
    private TableView<Service> tableServices;

    @FXML
    private TableColumn<Service, Long> id;

    @FXML
    private TableColumn<Service, String> name;

    @FXML
    private TableColumn<Service, String> mechanic;

    @FXML
    private TableColumn<Service, Double> quantity;

    @FXML
    private TableColumn<Service, BigDecimal> priceWithoutTax;

    @FXML
    private TableColumn<Service, Double> taxPercentage;

    @FXML
    private TableColumn<Service, String> note;

    @FXML
    private TableColumn<Service, Date> createdAt;

    @FXML
    private TableColumn<Service, Date> updatedAt;

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

    /**
     * Observable list with repairs for table in view
     */
    public static ObservableList<Service> services;

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
     * Path to language of main stage
     */
    private static final String LANGUAGE = "Modules/Repairs/Resources/Languages/repairs";

    /**
     * Set resource bundle
     */
    private ResourceBundle resourceBundle = LanguageService.getResourceBundle(LANGUAGE);

    /**
     * Constructor
     */
    public ServicesController() {
        services = FXCollections.observableArrayList();
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

        PricesService.calculationListenerPricesServices(services, propertyTotalWithoutTax, propertyTotalTax, propertyTotalWithTax);

        id.setCellValueFactory(new PropertyValueFactory("id"));
        name.setCellValueFactory(new PropertyValueFactory("name"));
        mechanic.setCellValueFactory(new PropertyValueFactory("mechanic"));
        quantity.setCellValueFactory(new PropertyValueFactory("quantity"));
        priceWithoutTax.setCellValueFactory(new PropertyValueFactory("priceWithoutTax"));
        taxPercentage.setCellValueFactory(new PropertyValueFactory("taxPercentage"));
        note.setCellValueFactory(new PropertyValueFactory("note"));
        createdAt.setCellValueFactory(new PropertyValueFactory("createdAt"));
        updatedAt.setCellValueFactory(new PropertyValueFactory("updatedAt"));
        tableServices.setItems(services);
    }

    /**
     * Ad item to services
     */
    @FXML
    public void add() {
        Service service = AddService.showDialog(null);
        if (service != null) {
            services.add(service);
        }
    }

    /**
     * Edit repair
     */
    @FXML
    public void edit() {
        Service selectService = tableServices.getSelectionModel().getSelectedItem();
        Service service = AddService.showDialog(selectService);
        services.set(services.indexOf(selectService), service);
    }

    /**
     * Delete car
     */
    @FXML
    private void delete() {
        if (!ConfirmationService.confirmDeleteService(resourceBundle)) {
            return;
        }
        Service service = tableServices.getSelectionModel().getSelectedItem();
        services.remove(service);
    }

    /**
     * Showing menu context
     *
     * @param e
     */
    @FXML
    protected void showingContextMenu(WindowEvent e) {
        Service service = tableServices.getSelectionModel().getSelectedItem();
        delete.setVisible(service != null);
        edit.setVisible(service != null);
    }
}
