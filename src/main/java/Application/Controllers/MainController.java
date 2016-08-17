package Application.Controllers;

import Application.Stages.Main;
import Modules.Cars.CarsServiceProvider;
import Modules.Repairs.RepairsServiceProvider;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: Aug 17, 2016
 * Time: 11:15:10 AM
 */
public class MainController implements Initializable {

    @FXML
    private TabPane tabPane;

    /**
     * Launch main stage
     */
    public static void launch(String[] args){
        Main.launch(Main.class, args);
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
        RepairsServiceProvider.launch(tabPane);
        CarsServiceProvider.launch(tabPane);
    }
}
