package Application.Controllers;

import java.net.URL;

import Application.Stages.LoaderMain;
import javafx.fxml.FXML;
import Application.Stages.Main;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
import Application.Services.TabsService;
import com.sun.javafx.application.LauncherImpl;

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
     * Controller
     */
    public MainController(){

    }

    /**
     * Launch main stage with loader view
     *
     * @param args arguments when the program starts
     */
    public static void launch(String[] args){
        LauncherImpl.launchApplication(Main.class, LoaderMain.class, args);
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
        TabsService.setTabPane(tabPane);
        //TabsService.addTab("/Modules/Cars/Resources/Views/ListCarsView.fxml", "Modules/Cars/Resources/Languages/cars", null);
        //TabsService.addTab("/Modules/Repairs/Resources/Views/ListRepairsView.fxml", "Modules/Repairs/Resources/Languages/repairs", null);
    }
}
