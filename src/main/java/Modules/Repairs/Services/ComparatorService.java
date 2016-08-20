package Modules.Repairs.Services;

import java.util.Comparator;
import Modules.Repairs.Models.Repair;

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
    public static Comparator<Repair> CREATED_AT = new Comparator<Repair>()
    {
        public int compare(Repair r1, Repair r2)
        {
            return (r1.getCreatedAt().getTime() > r2.getCreatedAt().getTime() ? -1 : 1);
        }
    };
}
