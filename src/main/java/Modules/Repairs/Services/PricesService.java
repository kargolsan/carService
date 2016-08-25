package Modules.Repairs.Services;

import Application.Services.ParseService;
import Modules.Repairs.Controllers.Items.PartsController;
import Modules.Repairs.Controllers.Items.ServicesController;
import Modules.Repairs.Models.Part;
import Modules.Repairs.Models.Service;
import javafx.beans.property.StringProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: 24.08.2016
 * Time: 19:53
 */
public class PricesService {

    /**
     * Calculation price for services
     *
     * @param services                also observable list
     * @param propertyTotalWithoutTax property for price total without tax
     * @param propertyTotalTax        property for total tax
     * @param propertyTotalWithTax    property for price total with tax
     */
    public static void calculationListenerPricesServices(ObservableList<Service> services, StringProperty propertyTotalWithoutTax, StringProperty propertyTotalTax, StringProperty propertyTotalWithTax) {
        services.addListener((ListChangeListener.Change<? extends Service> c) -> {
            BigDecimal countTotalWithoutTax = new BigDecimal(0);
            BigDecimal countTotalTax = new BigDecimal(0);
            BigDecimal countTotalWithTax = new BigDecimal(0);

            for (Service service : services) {
                BigDecimal priceWithoutTax = service.getPriceWithoutTax();
                Double tax = service.getTaxPercentage();
                Double quantity = service.getQuantity();

                if (priceWithoutTax == null || tax == null || quantity == null) {
                    continue;
                }

                priceWithoutTax = priceWithoutTax.multiply(new BigDecimal(quantity));
                BigDecimal priceWithTax = priceWithoutTax.add(service.getPriceWithoutTax().multiply(new BigDecimal(tax).divide(new BigDecimal(100))));
                if (quantity == 0) {
                    priceWithTax = priceWithoutTax;
                }
                countTotalWithoutTax = countTotalWithoutTax.add(priceWithoutTax);
                countTotalTax = countTotalTax.add(priceWithTax.subtract(priceWithoutTax));
                countTotalWithTax = countTotalWithTax.add(priceWithTax);
            }

            if (countTotalWithTax != null) {
                propertyTotalWithoutTax.setValue(countTotalWithoutTax.setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
                propertyTotalTax.setValue(countTotalTax.setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
                propertyTotalWithTax.setValue(countTotalWithTax.setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
            }
        });
    }

    /**
     * Calculation price for parts
     *
     * @param parts                   also observable list
     * @param propertyTotalWithoutTax property for price total without tax
     * @param propertyTotalTax        property for total tax
     * @param propertyTotalWithTax    property for price total with tax
     */
    public static void calculationListenerPricesParts(ObservableList<Part> parts, StringProperty propertyTotalWithoutTax, StringProperty propertyTotalTax, StringProperty propertyTotalWithTax) {
        parts.addListener((ListChangeListener.Change<? extends Part> c) -> {
            BigDecimal countTotalWithoutTax = new BigDecimal(0);
            BigDecimal countTotalTax = new BigDecimal(0);
            BigDecimal countTotalWithTax = new BigDecimal(0);

            for (Part part : parts) {
                BigDecimal priceWithoutTax = part.getPriceWithoutTax();
                Double tax = part.getTaxPercentage();
                Double quantity = part.getQuantity();

                if (priceWithoutTax == null || tax == null || quantity == null) {
                    continue;
                }

                priceWithoutTax = priceWithoutTax.multiply(new BigDecimal(quantity));
                BigDecimal priceWithTax = priceWithoutTax.add(part.getPriceWithoutTax().multiply(new BigDecimal(tax).divide(new BigDecimal(100))));
                if (quantity == 0) {
                    priceWithTax = priceWithoutTax;
                }
                countTotalWithoutTax = countTotalWithoutTax.add(priceWithoutTax);
                countTotalTax = countTotalTax.add(priceWithTax.subtract(priceWithoutTax));
                countTotalWithTax = countTotalWithTax.add(priceWithTax);
            }

            if (countTotalWithTax != null) {
                propertyTotalWithoutTax.setValue(countTotalWithoutTax.setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
                propertyTotalTax.setValue(countTotalTax.setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
                propertyTotalWithTax.setValue(countTotalWithTax.setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
            }
        });
    }

    /**
     * Calculation all prices for parts and services
     *
     * @param partsController of repair
     * @param servicesController of repair
     * @param paid is checkbox
     * @param depositField of repair
     * @param totalWithoutTax of label
     * @param totalTax of label
     * @param totalWithTax of label
     * @param totalToPayWithTax of label
     */
    public static void calculationTotalPrices(PartsController partsController, ServicesController servicesController, CheckBox paid,
                                              TextField depositField, Label totalWithoutTax, Label totalTax, Label totalWithTax, Label totalToPayWithTax) {

        BigDecimal partsTotalWithoutTax = ParseService.tryStringPropertyToBigDecimal(partsController.propertyTotalWithoutTax);
        BigDecimal partsTotalTax = ParseService.tryStringPropertyToBigDecimal(partsController.propertyTotalTax);
        BigDecimal partsTotalWithTax = ParseService.tryStringPropertyToBigDecimal(partsController.propertyTotalWithTax);

        BigDecimal servicesTotalWithoutTax = ParseService.tryStringPropertyToBigDecimal(servicesController.propertyTotalWithoutTax);
        BigDecimal servicesTotalTax = ParseService.tryStringPropertyToBigDecimal(servicesController.propertyTotalTax);
        BigDecimal servicesTotalWithTax = ParseService.tryStringPropertyToBigDecimal(servicesController.propertyTotalWithTax);

        totalWithoutTax.setText(partsTotalWithoutTax.add(servicesTotalWithoutTax).toString());
        totalTax.setText(partsTotalTax.add(servicesTotalTax).toString());
        totalWithTax.setText(partsTotalWithTax.add(servicesTotalWithTax).toString());

        BigDecimal deposit = ParseService.tryStringToBigDecimal(depositField.getText());
        totalToPayWithTax.setText(partsTotalWithTax.add(servicesTotalWithTax).subtract(deposit).toString());
        if (paid.isSelected()){
            totalToPayWithTax.setText("0");
        }
    }
}
