package Modules.Repairs.Controllers;

import Modules.Repairs.Models.Repair;
import Modules.Repairs.Repositories.RepairRepository;
import Modules.Repairs.Services.CellValueFactoryService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;


/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: 17.08.2016
 * Time: 15:43
 */
public class AddRepairController implements Initializable {

    @FXML
    private TextArea note;

    /**
     * Constructor
     */
    public AddRepairController()
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

    @FXML
    public void add(){
        Repair repair = new Repair();
        repair.setNote(note.getText());
        RepairRepository.add(repair);
        clear();
    }

    @FXML
    public void clear(){
        note.clear();
    }


}
