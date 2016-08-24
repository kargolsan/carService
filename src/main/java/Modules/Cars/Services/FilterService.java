package Modules.Cars.Services;

import Modules.Cars.Models.Car;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: 24.08.2016
 * Time: 11:03
 */
public class FilterService {

    /**
     * Add filtration for cars
     *
     * @param cars of observable list
     * @param wordFilter field in filter word
     * @param tableCars table with cars in view
     */
    public static void addFiltration(ObservableList<Car> cars, TextField wordFilter, TableView<Car> tableCars){
        FilteredList<Car> filteredCars = new FilteredList<>(cars, p -> true);

        wordFilter.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredCars.setPredicate(car -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (car.getRegistrationNumber().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (car.getPhones().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (car.getUser().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (car.getVin().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (car.getNote().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (car.getManufacturer().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (car.getEnginePower().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (car.getEngineModel().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (car.getEngineCapacity().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (car.getModel().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (car.getBody().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (car.getYearProduction().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (car.getFuel().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (CellsService.convertCreatedAt(car.getCreatedAt()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (CellsService.convertUpdatedAt(car.getUpdatedAt()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });

        SortedList<Car> sortedCars = new SortedList<>(filteredCars);

        sortedCars.comparatorProperty().bind(tableCars.comparatorProperty());

        tableCars.setItems(sortedCars);
    }
}
