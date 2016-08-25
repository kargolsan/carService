package Modules.Repairs.Controllers;

import java.net.URL;
import java.sql.Date;

import Modules.Repairs.Controllers.Items.PartsController;
import Modules.Repairs.Controllers.Items.ServicesController;
import Modules.Repairs.Models.Part;
import Modules.Repairs.Models.Service;
import Modules.Repairs.Services.PricesService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;

import java.time.ZoneId;
import java.time.LocalDate;

import Modules.Cars.Models.Car;

import java.util.HashSet;
import java.util.ResourceBundle;

import javafx.scene.control.*;
import javafx.fxml.Initializable;
import Modules.Repairs.Models.Repair;
import Modules.Cars.Stages.AssignCar;
import javafx.scene.layout.AnchorPane;
import Application.Services.TabsService;
import Application.Services.LanguageService;
import Application.Interfaces.IControllerTab;
import Modules.Cars.Repositories.CarRepository;
import Modules.Repairs.Repositories.RepairRepository;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: 17.08.2016
 * Time: 15:43
 */
public class EditRepairController implements Initializable, IControllerTab {

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
     * editable repair
     */
    private Repair repair;

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
    private static final String ICON = "/Modules/Repairs/Resources/Assets/Images/Icons/edit_20.png";

    /**
     * Set resource bundle
     */
    private ResourceBundle resourceBundle = LanguageService.getResourceBundle(LANGUAGE);

    /**
     * Constructor
     */
    public EditRepairController() {

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
     * @param repairId for controller
     * @param tab      of controller
     * @param lastTab  opened
     */
    @Override
    public void loaded(Object repairId, Tab tab, Tab lastTab) {
        this.repair = RepairRepository.get((Long) repairId);
        configurationTab(this.repair, tab, lastTab);

        if (this.repair.getDateStart() != null) {
            dateStart.setValue(this.repair.getDateStart().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        }
        if (this.repair.getDateEnd() != null) {
            dateEnd.setValue(this.repair.getDateEnd().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        }
        note.setText(this.repair.getNote());
        if (this.repair.getCarId() != null) {
            assignCar = CarRepository.get(this.repair.getCarId());
        }
        if (this.repair.getServices().size() > 0) {
            servicesController.services.addAll(this.repair.getServices());
        }
        if (this.repair.getParts().size() > 0) {
            partsController.parts.addAll(this.repair.getParts());
        }
        if (this.repair.getPaid() != null) {
            paid.setSelected(this.repair.getPaid());
        }
        if (assignCar != null) {
            setInfoAssignCar();
        }

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
        setPricesTotal();
    }

    /**
     * Add repair to database
     */
    @FXML
    public void update() {
        LocalDate dateStartValue = dateStart.getValue();
        LocalDate dateEndValue = dateEnd.getValue();
        if (dateStartValue != null) {
            repair.setDateStart(Date.valueOf(dateStartValue));
        }
        if (dateEndValue != null) {
            repair.setDateEnd(Date.valueOf(dateEndValue));
        }

        repair.setParts(new HashSet<Part>(partsController.parts));
        repair.setServices(new HashSet<Service>(servicesController.services));

        repair.setPaid(paid.isSelected());
        if (assignCar != null) {
            repair.setCarId(assignCar.getId());
        }
        repair.setNote(note.getText());
        RepairRepository.update(repair);

        Repair repairInList = ListRepairsController.repairs.stream().filter(p -> p.getId() == repair.getId()).findFirst().get();
        ListRepairsController.repairs.set(ListRepairsController.repairs.indexOf(repairInList), repair);
        close();
    }

    /**
     * Assign car to repair
     */
    @FXML
    public void assignCar() {
        assignCar = AssignCar.showDialog();
        if (assignCar != null) {
            setInfoAssignCar();
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
     * Set label with assign car to repair
     */
    private void setInfoAssignCar() {
        infoAssignCar.setText(String.format("%1$s", assignCar.getRegistrationNumber()));
    }

    /**
     * Configuration tab
     */
    private void configurationTab(Repair repair, Tab tab, Tab lastTab) {
        this.tab = tab;
        this.lastTab = lastTab;
        this.tab.setText(String.format(resourceBundle.getString("tab.edit_repair.title"), repair.getId()));
        TabsService.setIcon(this.tab, ICON);
    }

    /**
     * Set prices total for parts and services
     */
    public void setPricesTotal() {
        PricesService.calculationTotalPrices(partsController, servicesController, paid, deposit, totalWithoutTax, totalTax, totalWithTax, totalToPayWithTax);
    }
}
