package Application.Controllers.Menus;

import java.net.URL;
import java.util.ResourceBundle;

import Application.Services.TabsService;
import Modules.Cars.Controllers.ListCarsController;
import Modules.Cars.Models.Car;
import Modules.Cars.Repositories.CarRepository;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: Aug 17, 2016
 * Time: 11:15:10 AM
 */
public class ButtonsController implements Initializable {

    /**
     * Controller
     */
    public ButtonsController(){

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
     * Show tab with cars
     */
    @FXML
    public void cars(){
        TabsService.addTab("/Modules/Cars/Resources/Views/ListCarsView.fxml", "Modules/Cars/Resources/Languages/cars", null);
    }

    /**
     * Show tab with repairs
     */
    @FXML
    public void repairs(){
        TabsService.addTab("/Modules/Repairs/Resources/Views/ListRepairsView.fxml", "Modules/Repairs/Resources/Languages/repairs", null);
    }

}
