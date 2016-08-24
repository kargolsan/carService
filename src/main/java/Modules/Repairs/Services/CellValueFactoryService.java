package Modules.Repairs.Services;

import java.util.Date;

import Modules.Cars.Models.Car;
import javafx.util.Callback;
import java.text.SimpleDateFormat;
import Modules.Repairs.Models.Repair;
import javafx.scene.control.TableColumn;
import javafx.beans.value.ObservableValue;
import Modules.Cars.Repositories.CarRepository;
import javafx.beans.property.ReadOnlyObjectWrapper;

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

    /**
     * Factory value for date start of repair
     *
     * @return value factory
     */
    public Callback<TableColumn.CellDataFeatures<Repair, Date>, ObservableValue<Date>> propertyDateStartFactory(){
        return new Callback<TableColumn.CellDataFeatures<Repair, Date>, ObservableValue<Date>>() {
            public ObservableValue<Date> call(TableColumn.CellDataFeatures<Repair, Date> repair) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-dd-MM");

                Date data = repair.getValue().getDateStart();
                String result = null;

                if (data != null) {
                    result = formatter.format(repair.getValue().getDateStart());
                }
                return new ReadOnlyObjectWrapper(result);
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
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-dd-MM");

                Date data = repair.getValue().getDateEnd();
                String result = null;

                if (data != null) {
                    result = formatter.format(repair.getValue().getDateEnd());
                }
                return new ReadOnlyObjectWrapper(result);
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
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                Date data = repair.getValue().getUpdatedAt();
                String result = null;

                if (data != null) {
                    result = formatter.format(repair.getValue().getUpdatedAt());
                }
                return new ReadOnlyObjectWrapper(result);
            }
        };
    }

}
