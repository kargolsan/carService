package Modules.Repairs.Controllers;

import java.net.URL;
import java.sql.Date;
import javafx.fxml.FXML;
import java.time.ZoneId;
import java.time.LocalDate;
import Modules.Cars.Models.Car;
import java.util.ResourceBundle;
import javafx.scene.control.Tab;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import Modules.Repairs.Models.Repair;
import Modules.Cars.Stages.AssignCar;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.DatePicker;
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

    /** tab of this controller */
    private Tab tab;

    /** last opened tab */
    private Tab lastTab;

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

    /** editable repair */
    private Repair repair;

    /** Assign car to repair */
    private Car assignCar;

    /** Path to language of main stage */
    private static final String LANGUAGE = "Modules/Repairs/Resources/Languages/repairs";

    /** Set resource bundle */
    private ResourceBundle resourceBundle = LanguageService.getResourceBundle(LANGUAGE);

    /**
     * Constructor
     */
    public EditRepairController()
    {

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
     * @param repair for controller
     * @param tab of controller
     * @param lastTab opened
     */
    @Override
    public void loaded(Object repair, Tab tab, Tab lastTab) {
        configurationTab(tab, lastTab);
        this.repair = (Repair) repair;

        if (this.repair.getDateStart() != null){
            dateStart.setValue(this.repair.getDateStart().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        }
        if (this.repair.getDateEnd() != null){
            dateEnd.setValue(this.repair.getDateEnd().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        }
        note.setText(this.repair.getNote());
        if (this.repair.getCarId() != null){
            assignCar = CarRepository.get(this.repair.getCarId());
        }
        if (assignCar != null){
            setInfoAssignCar();
        }
    }

    /**
     * Add repair to database
     */
    @FXML
    public void update(){
        LocalDate dateStartValue = dateStart.getValue();
        LocalDate dateEndValue = dateEnd.getValue();
        if (dateStartValue != null){
            repair.setDateStart(Date.valueOf(dateStartValue));
        }
        if (dateEndValue != null){
            repair.setDateEnd(Date.valueOf(dateEndValue));
        }
        if (assignCar != null){
            repair.setCarId(assignCar.getId());
        }
        repair.setNote(note.getText());
        RepairRepository.update(repair);
        ListRepairsController.repairs.set(ListRepairsController.repairs.indexOf(repair), repair);
        close();
    }

    /**
     * Assign car to repair
     */
    @FXML
    public void assignCar(){
        assignCar = AssignCar.showDialog();
        if (assignCar != null){
            setInfoAssignCar();
        }
    }

    /**
     * Close tab
     */
    public void close(){
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
    private void configurationTab(Tab tab, Tab lastTab){
        this.tab = tab;
        this.lastTab = lastTab;
        this.tab.setText(resourceBundle.getString("tab.edit_repair.title"));
    }
}
