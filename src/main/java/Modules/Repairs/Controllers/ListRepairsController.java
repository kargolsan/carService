package Modules.Repairs.Controllers;

import java.net.URL;
import java.util.Date;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.WindowEvent;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import Modules.Repairs.Models.Repair;
import javafx.collections.FXCollections;
import Application.Services.TabsService;
import javafx.collections.ObservableList;
import Application.Services.LanguageService;
import Application.Interfaces.IControllerTab;
import Modules.Repairs.Services.FilterService;
import Modules.Repairs.Services.ConfirmationService;
import Modules.Repairs.Repositories.RepairRepository;
import javafx.scene.control.cell.PropertyValueFactory;
import Modules.Repairs.Services.CellValueFactoryService;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: 17.08.2016
 * Time: 15:43
 */
public class ListRepairsController implements Initializable, IControllerTab {

    /** tab of this controller */
    private Tab tab;

    /** last opened tab */
    private Tab lastTab;

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

    @FXML
    private MenuItem edit;

    @FXML
    private TextField wordFilter;

    /** Observable list with repairs for table in view */
    public static ObservableList<Repair> repairs;

    /** Path to language of main stage */
    private static final String LANGUAGE = "Modules/Repairs/Resources/Languages/repairs";

    /** Path to icon of tab */
    private static final String ICON = "/Modules/Repairs/Resources/Assets/Images/Icons/repairs.png";

    /** Set resource bundle */
    private ResourceBundle resourceBundle = LanguageService.getResourceBundle(LANGUAGE);

    /**
     * Constructor
     */
    public ListRepairsController()
    {
        repairs = FXCollections.observableArrayList();
        repairs.addAll(RepairRepository.sortCreatedAtDesc(RepairRepository.getAll()));
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
        dateStart.setCellValueFactory(new CellValueFactoryService().propertyDateStartFactory());
        dateEnd.setCellValueFactory(new CellValueFactoryService().propertyDateEndFactory());
        note.setCellValueFactory(new PropertyValueFactory("note"));
        carId.setCellValueFactory(new PropertyValueFactory("carId"));
        carRegistrationNumber.setCellValueFactory(new CellValueFactoryService().propertyRegistrationNumberFactory());
        createdAt.setCellValueFactory(new CellValueFactoryService().propertyCreatedAtFactory());
        updatedAt.setCellValueFactory(new CellValueFactoryService().propertyUpdatedAtFactory());
        tableRepairs.setItems(repairs);

        addFiltration();
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
     * Cancel view
     */
    @FXML
    public void cancel(){
        TabsService.tabPane.getTabs().remove(tab);
        TabsService.tabPane.getSelectionModel().select(lastTab);
    }

    /**
     * Refresh repairs
     */
    @FXML
    public void refresh(){
        repairs.clear();
        repairs.addAll(RepairRepository.sortCreatedAtDesc(RepairRepository.getAll()));
    }

    /**
     * Add filtration for cars
     */
    private void addFiltration() {
        FilterService.addFiltration(repairs, wordFilter, tableRepairs);
    }

    /**
     * Add repair
     */
    @FXML
    public void add(){
        TabsService.addTab("/Modules/Repairs/Resources/Views/AddRepairView.fxml", "Modules/Repairs/Resources/Languages/repairs", null);
    }

    /**
     * Edit repair
     */
    @FXML
    public void edit(){
        TabsService.addTab(
                "/Modules/Repairs/Resources/Views/EditRepairView.fxml",
                "Modules/Repairs/Resources/Languages/repairs",
                tableRepairs.getSelectionModel().getSelectedItem().getId()
        );
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
        edit.setVisible(repair != null);
    }

    /**
     * Delete car
     */
    @FXML
    private void delete()
    {
        if (!ConfirmationService.confirmDeleteRepair(resourceBundle)) { return; }
        Repair repair = tableRepairs.getSelectionModel().getSelectedItem();
        Boolean result = RepairRepository.delete(repair.getId());
        if (result){
            repairs.remove(repair);
        }
    }

    /**
     * Configuration tab
     */
    private void configurationTab(Tab tab, Tab lastTab){
        this.tab = tab;
        this.lastTab = lastTab;
        this.tab.setText(resourceBundle.getString("tab.list_repairs.title"));
        TabsService.setIcon(this.tab, ICON);
    }
}
