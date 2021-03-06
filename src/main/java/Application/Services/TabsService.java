package Application.Services;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Tab;
import java.util.ResourceBundle;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import Application.Interfaces.IControllerTab;
import javafx.scene.layout.HBox;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: 17.08.2016
 * Time: 14:37
 */
public class TabsService {

    /** tabPane in main view of application */
    static public TabPane tabPane;

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
     * @param options for content of tab
     */
    public static void addTab(String pathView, String resourceBundlePath, Object options){
        Tab lastTab = tabPane.getSelectionModel().getSelectedItem();
        try {
            ResourceBundle resourceBundle = LanguageService.getResourceBundle(resourceBundlePath);
            FXMLLoader loader = new FXMLLoader(TabsService.class.getResource(pathView), resourceBundle);
            AnchorPane anchorPane = loader.load();
            Tab tab = new Tab();
            IControllerTab controller = loader.getController();
            controller.loaded(options, tab, lastTab);
            tab.setContent(anchorPane);
            //tab = setIconTab(tab, icon);
            tabPane.getTabs().add(tab);
            tabPane.getSelectionModel().select(tab);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Set icon for tab
     *
     * @param tab for icon
     * @param icon for tab
     */
    public static void setIcon(Tab tab, String icon){
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(0, 3, 0, 0));
        ImageView imageView = new ImageView(new Image(TabsService.class.getResource(icon).toExternalForm()));
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(18);
        hbox.getChildren().add(imageView);
        tab.setGraphic(hbox);
    }
}
