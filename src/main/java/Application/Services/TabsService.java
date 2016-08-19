package Application.Services;

import java.io.IOException;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: 17.08.2016
 * Time: 14:37
 */
public class TabsService {

    /** tabPane in main view of application */
    static private TabPane tabPane;

    /**
     * Set tabPane in main view of application
     *
     * @param tabPane in main view of application
     */
    public static void setTabPane(TabPane tabPane){
        TabsService.tabPane = tabPane;
    }

    /**
     * Add tab to tab container in main view of application
     *
     * @param pathView to view
     * @param resourceBundlePath path to resource bundle
     */
    public static void addTab(String pathView, String resourceBundlePath){
        try {
            ResourceBundle resourceBundle = LanguageService.getResourceBundle(resourceBundlePath);
            Tab tab = FXMLLoader.load(TabsService.class.getResource(pathView), resourceBundle);
            tabPane.getTabs().add(tab);
            tabPane.getSelectionModel().select(tab);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
