package Modules.Repairs.Services;

import java.text.SimpleDateFormat;
import Modules.Cars.Repositories.CarRepository;

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

    /**
     * Convert date start of repair for table view
     *
     * @param date of start of repair
     * @return formatted date
     */
    public static String convertDateStart(Object date){
        if (date != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-dd-MM");
            return formatter.format(date);
        }
        return "";
    }

    /**
     * Convert date end of repair for table view
     *
     * @param date of end of repair
     * @return formatted date
     */
    public static String convertDateEnd(Object date){
        if (date != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-dd-MM");
            return formatter.format(date);
        }
        return "";
    }

    /**
     * Convert id car to registration number
     *
     * @param carId of end of repair
     * @return formatted date
     */
    public static String convertCarIdToRegistrationNumber(Object carId){
        if (carId != null) {
            return CarRepository.get((Long)carId).getRegistrationNumber();
        }
        return "";
    }
}
