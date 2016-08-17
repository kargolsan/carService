import Application.Controllers.MainController;
import Database.Services.SessionService;
import Modules.Cars.Models.Car;
import Modules.Cars.Repositories.CarRepository;
import Modules.Repairs.Models.Repair;
import Modules.Repairs.Repositories.RepairRepository;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: Aug 17, 2016
 * Time: 11:15:10 AM
 */
public class start {
    /**
     * Main function for application
     *
     * @param args arguments when the program starts
     */
    public static void main(String[] args) {


        Car car = new Car();
        car.setNote("Example note for car");
        car.setRegistrationNumber("TSA6127");
        car = CarRepository.add(car);
        car.setNote("update note for car");
        CarRepository.update(car);

        Repair repair = new Repair();
        repair.setNote("Example note");
        repair.setCarId(car.getId());
        repair = RepairRepository.add(repair);
        repair.setNote("update note");
        RepairRepository.update(repair);

        MainController.launch(args);
        SessionService.close();
    }
}
