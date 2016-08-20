package Modules.Cars.Services;

import java.util.Comparator;
import Modules.Cars.Models.Car;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: 20.08.2016
 * Time: 07:44
 */
public class ComparatorService {

    /**
     * Comparator for created at of property object
     */
    public static Comparator<Car> CREATED_AT = new Comparator<Car>()
    {
        public int compare(Car c1, Car c2)
        {
            return (c1.getCreatedAt().getTime() > c2.getCreatedAt().getTime() ? -1 : 1);
        }
    };
}
