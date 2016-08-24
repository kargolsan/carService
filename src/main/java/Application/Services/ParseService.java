package Application.Services;

import javafx.beans.property.StringProperty;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: 24.08.2016
 * Time: 15:07
 */
public class ParseService {

    /**
     * Try parse string to integer without error
     *
     * @param string to parse
     * @return integer or null
     */
    public static Integer tryStringToInteger(String string) {
        try {
            return Integer.parseInt(string);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Try parse string to big decimal without error
     *
     * @param string to parse
     * @return big decimal or null
     */
    public static BigDecimal tryStringToBigDecimal(String string) {
        try {
            return new BigDecimal(string.replaceAll(",", "."));
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

    /**
     * Try parse string to double without error
     *
     * @param string to parse
     * @return double or null
     */
    public static Double tryStringToDouble(String string) {
        try {
            return Double.parseDouble(string.replaceAll(",", "."));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Try parse object to string
     *
     * @param object to parse
     * @return double or null
     */
    public static String tryObjectToString(Object object) {
        try {
            if (object != null) {
                return object.toString();
            }
        } catch (Exception e) {

        }
        return "";
    }

    /**
     * Try parse string property to big decimal
     *
     * @param stringProperty for parse
     * @return big decimal
     */
    public static BigDecimal tryStringPropertyToBigDecimal(StringProperty stringProperty) {
        try {
            if (stringProperty != null) {
                return new BigDecimal(stringProperty.get());
            } else {
                return BigDecimal.ZERO;
            }
        } catch (Exception e) {

        }
        return BigDecimal.ZERO;
    }
}
