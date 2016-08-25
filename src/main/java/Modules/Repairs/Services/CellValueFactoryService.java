package Modules.Repairs.Services;

import java.util.Date;
import java.util.ResourceBundle;

import Application.Services.LanguageService;
import javafx.util.Callback;
import Modules.Repairs.Models.Repair;
import javafx.scene.control.TableColumn;
import javafx.beans.value.ObservableValue;
import javafx.beans.property.ReadOnlyObjectWrapper;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: 17.08.2016
 * Time: 17:14
 */
public class CellValueFactoryService {


    /** Path to language of main stage */
    private static final String LANGUAGE = "Modules/Repairs/Resources/Languages/repairs";

    /** Set resource bundle */
    private ResourceBundle resourceBundle = LanguageService.getResourceBundle(LANGUAGE);

    /**
     * Factory value for registration number
     *
     * @return value factory
     */
    public Callback<TableColumn.CellDataFeatures<Repair, String>, ObservableValue<String>> propertyRegistrationNumberFactory(){
        return new Callback<TableColumn.CellDataFeatures<Repair, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Repair, String> repair) {
                return new ReadOnlyObjectWrapper(CellsService.convertCarIdToRegistrationNumber(repair.getValue().getCarId()));
            }
        };
    }

    /**
     * Factory value for date and time
     *
     * @return value factory
     */
    public Callback<TableColumn.CellDataFeatures<Repair, Date>, ObservableValue<Date>> propertyCreatedAtFactory(){
        return new Callback<TableColumn.CellDataFeatures<Repair, Date>, ObservableValue<Date>>() {
            public ObservableValue<Date> call(TableColumn.CellDataFeatures<Repair, Date> repair) {
                return new ReadOnlyObjectWrapper(CellsService.convertCreatedAt(repair.getValue().getCreatedAt()));
            }
        };
    }

    /**
     * Factory value for date start of repair
     *
     * @return value factory
     */
    public Callback<TableColumn.CellDataFeatures<Repair, Date>, ObservableValue<Date>> propertyDateStartFactory(){
        return new Callback<TableColumn.CellDataFeatures<Repair, Date>, ObservableValue<Date>>() {
            public ObservableValue<Date> call(TableColumn.CellDataFeatures<Repair, Date> repair) {
                return new ReadOnlyObjectWrapper(CellsService.convertDateStart(repair.getValue().getDateStart()));
            }
        };
    }

    /**
     * Factory value for date end of repair
     *
     * @return value factory
     */
    public Callback<TableColumn.CellDataFeatures<Repair, Date>, ObservableValue<Date>> propertyDateEndFactory(){
        return new Callback<TableColumn.CellDataFeatures<Repair, Date>, ObservableValue<Date>>() {
            public ObservableValue<Date> call(TableColumn.CellDataFeatures<Repair, Date> repair) {
                return new ReadOnlyObjectWrapper(CellsService.convertDateEnd(repair.getValue().getDateEnd()));
            }
        };
    }

    /**
     * Factory value for date and time
     *
     * @return value factory
     */
    public Callback<TableColumn.CellDataFeatures<Repair, Date>, ObservableValue<Date>> propertyUpdatedAtFactory(){
        return new Callback<TableColumn.CellDataFeatures<Repair, Date>, ObservableValue<Date>>() {
            public ObservableValue<Date> call(TableColumn.CellDataFeatures<Repair, Date> repair) {
                return new ReadOnlyObjectWrapper(CellsService.convertUpdatedAt(repair.getValue().getUpdatedAt()));
            }
        };
    }

    /**
     * Factory value for paid
     *
     * @return value factory
     */
    public Callback<TableColumn.CellDataFeatures<Repair, Boolean>, ObservableValue<Boolean>> propertyPaidFactory(){
        return new Callback<TableColumn.CellDataFeatures<Repair, Boolean>, ObservableValue<Boolean>>() {
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Repair, Boolean> repair) {
                if (repair.getValue().getPaid() == null){
                    return new ReadOnlyObjectWrapper(resourceBundle.getString("tab.list_repairs.table_view.value.no"));
                }
                if (!repair.getValue().getPaid()){
                    return new ReadOnlyObjectWrapper(resourceBundle.getString("tab.list_repairs.table_view.value.no"));
                } else {
                    return new ReadOnlyObjectWrapper(resourceBundle.getString("tab.list_repairs.table_view.value.yes"));
                }
            }
        };
    }

}
