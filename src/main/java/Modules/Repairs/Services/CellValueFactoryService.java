package Modules.Repairs.Services;

import Modules.Cars.Repositories.CarRepository;
import Modules.Repairs.Models.Repair;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: 17.08.2016
 * Time: 17:14
 */
public class CellValueFactoryService {

    /**
     * Factory value for registration number
     *
     * @return value factory
     */
    public Callback<TableColumn.CellDataFeatures<Repair, String>, ObservableValue<String>> propertyRegistrationNumberFactory(){
        return new Callback<TableColumn.CellDataFeatures<Repair, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Repair, String> repair) {
                String registrationNumber = "";
                Long carId = repair.getValue().getCarId();
                if (carId != null){
                    registrationNumber = CarRepository.get(carId).getRegistrationNumber();
                }
                return new ReadOnlyObjectWrapper(registrationNumber);
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
                SimpleDateFormat formatter = new SimpleDateFormat("EEEE d MMMM HH:mm yyyy");

                Date data = repair.getValue().getCreatedAt();
                String result = null;

                if (data != null) {
                    result = formatter.format(repair.getValue().getCreatedAt());
                }
                return new ReadOnlyObjectWrapper(result);
            }
        };
    }

}
