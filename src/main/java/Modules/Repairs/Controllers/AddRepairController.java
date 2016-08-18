package Modules.Repairs.Controllers;

import Modules.Cars.Models.Car;
import Modules.Repairs.Models.Repair;
import Modules.Repairs.Repositories.RepairRepository;
import Modules.Cars.Stages.AssignCar;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;


/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: 17.08.2016
 * Time: 15:43
 */
public class AddRepairController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private DatePicker dateStart;

    @FXML
    private DatePicker dateEnd;

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

    /**
     * Add repair to database
     */
    @FXML
    public void add(){
        Repair repair = new Repair();
        repair.setDateStart(Date.valueOf(dateStart.getValue()));
        repair.setDateEnd(Date.valueOf(dateEnd.getValue()));
        repair.setNote(note.getText());
        RepairRepository.add(repair);
        clear();
    }

    /**
     * Clear data in view
     */
    @FXML
    public void clear(){
        dateStart.setValue(null);
        dateEnd.setValue(null);
        note.clear();
    }

    /**
     * Assign car to repair
     */
    @FXML
    public void assignCar(){

        Car result = AssignCar.showDialog();
        if (result != null){
            System.out.println(result.getNote());
        }


    }


}
