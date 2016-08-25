package Modules.Repairs.Controllers;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.Date;

import Application.Services.ParseService;
import Modules.Repairs.Controllers.Items.PartsController;
import Modules.Repairs.Controllers.Items.ServicesController;
import Modules.Repairs.Models.Part;
import Modules.Repairs.Models.Service;
import Modules.Repairs.Services.PricesService;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;

import java.time.LocalDate;

import Modules.Cars.Models.Car;
import javafx.scene.control.*;

import java.util.HashSet;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import Modules.Cars.Stages.AssignCar;
import Modules.Repairs.Models.Repair;
import javafx.scene.layout.AnchorPane;
import Application.Services.TabsService;
import Application.Services.LanguageService;
import Application.Interfaces.IControllerTab;
import Modules.Repairs.Repositories.RepairRepository;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: 17.08.2016
 * Time: 15:43
 */
public class AddRepairController implements Initializable, IControllerTab {

    /**
     * tab of this controller
     */
    private Tab tab;

    /**
     * last opened tab
     */
    private Tab lastTab;

    /**
     * services for add repair
     */
    @FXML
    private ServicesController servicesController;

    /**
     * parts for add repair
     */
    @FXML
    private PartsController partsController;

    @FXML
    private AnchorPane root;

    @FXML
    private DatePicker dateStart;

    @FXML
    private DatePicker dateEnd;

    @FXML
    private TextArea note;

    @FXML
    private Label infoAssignCar;

    @FXML
    private Label totalWithoutTax;

    @FXML
    private Label totalTax;

    @FXML
    private Label totalWithTax;

    @FXML
    private Label totalToPayWithTax;

    @FXML
    private TextField deposit;

    @FXML
    private CheckBox paid;

    /**
     * Assign car to repair
     */
    private Car assignCar;

    /**
     * Path to language of main stage
     */
    private static final String LANGUAGE = "Modules/Repairs/Resources/Languages/repairs";

    /**
     * Path to icon of tab
     */
    private static final String ICON = "/Modules/Repairs/Resources/Assets/Images/Icons/add_20.png";

    /**
     * Set resource bundle
     */
    private ResourceBundle resourceBundle = LanguageService.getResourceBundle(LANGUAGE);

    /**
     * Constructor
     */
    public AddRepairController() {

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
        configurationTab(tab, lastTab);
        partsController.propertyTotalWithTax.addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                setPricesTotal();
            }
        });
        servicesController.propertyTotalWithTax.addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                setPricesTotal();
            }
        });
        deposit.textProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                setPricesTotal();
            }
        });
        paid.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue observable, Boolean oldValue, Boolean newValue) {
                setPricesTotal();
            }
        });
    }

    /**
     * Add repair to database
     */
    @FXML
    public void add() {
        Repair repair = new Repair();
        LocalDate dateStartValue = dateStart.getValue();
        LocalDate dateEndValue = dateEnd.getValue();
        if (dateStartValue != null) {
            repair.setDateStart(Date.valueOf(dateStartValue));
        }
        if (dateEndValue != null) {
            repair.setDateEnd(Date.valueOf(dateEndValue));
        }
        if (assignCar != null) {
            repair.setCarId(assignCar.getId());
        }
        repair.setPaid(paid.isSelected());
        if (partsController.parts.size() > 0) {
            repair.setParts(new HashSet<Part>(partsController.parts));
        }
        if (servicesController.services.size() > 0) {
            repair.setServices(new HashSet<Service>(servicesController.services));
        }
        repair.setNote(note.getText());
        repair = RepairRepository.add(repair);
        ListRepairsController.repairs.add(0, repair);
        close();
    }

    /**
     * Assign car to repair
     */
    @FXML
    public void assignCar() {
        assignCar = AssignCar.showDialog();
        if (assignCar != null) {
            infoAssignCar.setText(String.format("%1$s", assignCar.getRegistrationNumber()));
        }
    }

    /**
     * Change paid for repair
     */
    @FXML
    public void paid() {

    }

    /**
     * Cancel from view
     */
    @FXML
    public void cancel() {
        close();
    }

    /**
     * Close tab
     */
    public void close() {
        TabsService.tabPane.getTabs().remove(tab);
        TabsService.tabPane.getSelectionModel().select(lastTab);
    }

    /**
     * Configuration tab
     */
    private void configurationTab(Tab tab, Tab lastTab) {
        this.tab = tab;
        this.lastTab = lastTab;
        this.tab.setText(resourceBundle.getString("tab.add_repair.title"));
        TabsService.setIcon(this.tab, ICON);
    }

    /**
     * Set prices total for parts and services
     */
    public void setPricesTotal() {
        PricesService.calculationTotalPrices(partsController, servicesController, paid,  deposit, totalWithoutTax, totalTax, totalWithTax, totalToPayWithTax);
    }
}
