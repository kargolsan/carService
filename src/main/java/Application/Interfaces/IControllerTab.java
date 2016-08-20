package Application.Interfaces;

import javafx.scene.control.Tab;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: 20.08.2016
 * Time: 10:16
 */
public interface IControllerTab {

    /**
     * loaded after initialized controller
     *
     * @param options for controller
     * @param tab of controller
     * @param lastTab opened
     */
    void loaded(Object options, Tab tab, Tab lastTab);
}
