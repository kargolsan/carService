package Modules.Cars.Services;

import java.util.Date;
import javafx.util.Callback;
import Modules.Cars.Models.Car;
import java.text.SimpleDateFormat;
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

    /**
     * Factory value for date and time
     *
     * @return value factory
     */
    public Callback<TableColumn.CellDataFeatures<Car, Date>, ObservableValue<Date>> propertyCreatedAtFactory(){
        return new Callback<TableColumn.CellDataFeatures<Car, Date>, ObservableValue<Date>>() {
            public ObservableValue<Date> call(TableColumn.CellDataFeatures<Car, Date> car) {
                return new ReadOnlyObjectWrapper(CellsService.convertCreatedAt(car.getValue().getCreatedAt()));
            }
        };
    }

    /**
     * Factory value for date and time
     *
     * @return value factory
     */
    public Callback<TableColumn.CellDataFeatures<Car, Date>, ObservableValue<Date>> propertyUpdatedAtFactory(){
        return new Callback<TableColumn.CellDataFeatures<Car, Date>, ObservableValue<Date>>() {
            public ObservableValue<Date> call(TableColumn.CellDataFeatures<Car, Date> car) {
                return new ReadOnlyObjectWrapper(CellsService.convertUpdatedAt(car.getValue().getUpdatedAt()));
            }
        };
    }

}
