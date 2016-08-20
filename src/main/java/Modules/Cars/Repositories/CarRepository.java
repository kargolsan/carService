package Modules.Cars.Repositories;

import java.util.List;
import java.util.Date;
import java.util.Collections;
import org.hibernate.Session;
import Modules.Cars.Models.Car;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import Database.Services.SessionService;
import Modules.Cars.Services.ComparatorService;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: 17.08.2016
 * Time: 13:36
 */
public class CarRepository {

    /**
     * Add car to database
     *
     * @param car to database
     * @return added car
     */
    public static Car add(Car car) {
        Session session = SessionService.getSessionFactory().openSession();
        Transaction transaction = null;
        Long id = null;
        car.setCreatedAt(new Date());
        try {
            transaction = session.beginTransaction();
            id = (Long) session.save(car);
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        session.close();
        return get(id);
    }

    /**
     * Get car by id of car from database
     *
     * @param id of car in database
     * @return car
     */
    public static Car get(Long id) {
        Car car = null;
        Session session = SessionService.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            car = session.get(Car.class, id);
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        session.close();
        return car;
    }

    /**
     * Get all cars from database
     *
     * @return all cars from database
     */
    public static List<Car> getAll() {
        List<Car> cars = null;
        Session session = SessionService.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Car");
            cars = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        session.close();
        return cars;
    }

    /**
     * Update car in database
     *
     * @param car in database
     */
    public static void update(Car car){
        Session session = SessionService.getSessionFactory().openSession();
        Transaction transaction = null;
        car.setUpdatedAt(new Date());
        try {
            transaction = session.beginTransaction();
            session.update(car);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        session.close();
    }

    /**
     * Delete car in database
     *
     * @param id car in database
     * @return true if removed car in database, false if not removed car to database
     */
    public static Boolean delete(Long id) {
        Boolean result = false;
        Session session = SessionService.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("delete from Car where ID=:ID");
            query.setParameter("ID", id);
            result = (query.executeUpdate() == 1);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        session.close();
        return result;
    }

    /**
     * Sort cars by created at descending
     * @param cars
     * @return
     */
    public static List<Car> sortCreatedAtDesc(List<Car> cars){
        Collections.sort(cars, ComparatorService.CREATED_AT);
        return cars;
    }
}
