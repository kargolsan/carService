package Modules.Repairs.Services;

import Modules.Repairs.Models.Repair;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.collections.transformation.FilteredList;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: 24.08.2016
 * Time: 11:03
 */
public class FilterService {

    /**
     * Add filtration for repairs
     *
     * @param repairs of observable list
     * @param wordFilter field in filter word
     * @param tableRepairs table with cars in view
     */
    public static void addFiltration(ObservableList<Repair> repairs, TextField wordFilter, TableView<Repair> tableRepairs){
        FilteredList<Repair> filteredRepairs = new FilteredList<>(repairs, p -> true);

        wordFilter.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredRepairs.setPredicate(repair -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (repair.getCarId().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (CellsService.convertDateStart(repair.getDateStart()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (CellsService.convertDateEnd(repair.getDateEnd()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (repair.getNote().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (CellsService.convertCreatedAt(repair.getCreatedAt()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (CellsService.convertUpdatedAt(repair.getUpdatedAt()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (CellsService.convertCarIdToRegistrationNumber(repair.getCarId()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });

        SortedList<Repair> sortedRepairs = new SortedList<>(filteredRepairs);

        sortedRepairs.comparatorProperty().bind(tableRepairs.comparatorProperty());

        tableRepairs.setItems(sortedRepairs);
    }
}
