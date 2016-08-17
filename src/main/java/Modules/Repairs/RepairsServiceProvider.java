package Modules.Repairs;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: 17.08.2016
 * Time: 14:37
 */
public class RepairsServiceProvider {

    /** Path to view of tab with repairs */
    private static final String VIEW = "/Modules/Repairs/Resources/Views/ListRepairsTabView.fxml";

    /**
     * Launch tab to the tab container
     *
     * @param tabPane is the tab container
     */
    public static void launch(TabPane tabPane){
        try {
            Tab tab = FXMLLoader.load(RepairsServiceProvider.class.getResource(VIEW));
            tabPane.getTabs().add(tab);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
