package Application.Controllers;

import java.net.URL;

import Application.Services.LanguageService;
import javafx.fxml.FXML;
import Application.Stages.Main;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
import Application.Services.TabsService;
import javafx.stage.Stage;

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
        TabsService.setTabPane(tabPane);
        TabsService.addTab("/Modules/Cars/Resources/Views/Tabs/ListCarsView.fxml", "Modules/Cars/Resources/Languages/cars");
        TabsService.addTab("/Modules/Repairs/Resources/Views/Tabs/ListRepairsView.fxml", "Modules/Repairs/Resources/Languages/repairs");
    }
}
