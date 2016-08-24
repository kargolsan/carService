package Modules.Repairs.Controllers.Dialogs;

import Application.Interfaces.IControllerDialog;
import Application.Services.LanguageService;
import Application.Services.ParseService;
import Application.Classes.StageDialog;
import Application.Services.StageServices;
import Modules.Repairs.Models.Service;
import Modules.Repairs.Repositories.RepairRepository;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: 18.08.2016
 * Time: 12:40
 */
public class AddServiceController implements Initializable, IControllerDialog {

    @FXML
    private VBox root;

    @FXML
    private TextField name;

    @FXML
    private TextField mechanic;

    @FXML
    private TextField quantity;

    @FXML
    private TextField priceWithoutTax;

    @FXML
    private TextField taxPercentage;

    @FXML
    private TextField note;

    /** Path to language of main stage */
    private static final String LANGUAGE = "Modules/Repairs/Resources/Languages/repairs";

    /** Set resource bundle */
    private ResourceBundle resourceBundle = LanguageService.getResourceBundle(LANGUAGE);

    /**
     * result of this dialog
     */
    private Service result = null;

    /**
     * Constructor
     */
    public AddServiceController() {
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
     * loaded after initialized controller
     *
     * @param options for controller
     */
    @Override
    public void loaded(Object options) {
        if (options != null){
            result = (Service) options;
            StageServices.setTitleDialogByRoot((Stage)root.getScene().getWindow(), resourceBundle.getString("tab.repair.services.edit_service.title"));

            name.setText(result.getName());
            mechanic.setText(result.getMechanic());
            quantity.setText(ParseService.tryObjectToString(result.getQuantity()));
            priceWithoutTax.setText(ParseService.tryObjectToString(result.getPriceWithoutTax()));
            taxPercentage.setText(ParseService.tryObjectToString(result.getTaxPercentage()));
            note.setText(result.getNote());
        } else {
            result = new Service();
        }
    }

    /**
     * Accept and exit dialog with result object
     */
    @FXML
    public void assign() {
        result.setName(name.getText());
        result.setMechanic(mechanic.getText());
        result.setQuantity(ParseService.tryStringToDouble(quantity.getText()));
        result.setPriceWithoutTax(ParseService.tryStringToBigDecimal(priceWithoutTax.getText()));
        result.setTaxPercentage(ParseService.tryStringToDouble(taxPercentage.getText()));
        result.setNote(note.getText());
        result.setCreatedAt(new Date());
        close();
    }

    /**
     * Exit dialog with null result
     */
    @FXML
    public void cancel() {
        close();
    }

    /**
     * Close stage
     */
    private void close() {
        StageDialog stage = (StageDialog) root.getScene().getWindow();
        stage.setResult(result);
        stage.close();
    }
}
