package Modules.Cars.Services;

import java.text.SimpleDateFormat;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: 24.08.2016
 * Time: 10:22
 */
public class CellsService {

    /**
     * Convert date created at to date for table view
     *
     * @param date of created at
     * @return formatted date
     */
    public static String convertCreatedAt(Object date){
        if (date != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("EEEE d MMMM HH:mm yyyy");
            return formatter.format(date);
        }
        return "";
    }

    /**
     * Convert date updated at to date for table view
     *
     * @param date of updated at
     * @return formatted date
     */
    public static String convertUpdatedAt(Object date){
        if (date != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return formatter.format(date);
        }
        return "";
    }
}
