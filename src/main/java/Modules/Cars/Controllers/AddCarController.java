package Modules.Cars.Controllers;

import Modules.Cars.Models.Car;
import Modules.Cars.Repositories.CarRepository;
import Modules.Cars.Stages.AssignCar;
import Modules.Repairs.Models.Repair;
import Modules.Repairs.Repositories.RepairRepository;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
public class AddCarController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private TextField registrationNumber;

    @FXML
    private TextField vin;

    @FXML
    private TextArea note;

    /**
     * Constructor
     */
    public AddCarController()
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
        Car car = new Car();
        car.setRegistrationNumber(registrationNumber.getText());
        car.setVin(vin.getText());
        car.setNote(note.getText());
        CarRepository.add(car);
        clear();
    }

    /**
     * Clear data in view
     */
    @FXML
    public void clear(){
        registrationNumber.setText("");
        vin.setText("");
        note.clear();
    }
}
