package Modules.Repairs.Repositories;

import java.util.List;
import java.util.Date;

import Modules.Repairs.Models.Part;
import Modules.Repairs.Models.Service;
import org.hibernate.Session;
import java.util.Collections;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import Modules.Repairs.Models.Repair;
import Database.Services.SessionService;
import Modules.Repairs.Services.ComparatorService;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: 17.08.2016
 * Time: 13:36
 */
public class RepairRepository {

    /**
     * Add repair to database
     *
     * @param repair
     * @return added repair
     */
    public static Repair add(Repair repair) {
        Session session = SessionService.getSessionFactory().openSession();
        Transaction transaction = null;
        Long id = null;
        repair.setCreatedAt(new Date());
        try {
            transaction = session.beginTransaction();
            id = (Long) session.save(repair);
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        session.close();
        return get(id);
    }

    /**
     * Get repair by id of repair from database
     *
     * @param id of repair in database
     * @return repair
     */
    public static Repair get(Long id) {
        Repair repair = null;
        Session session = SessionService.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            repair = session.get(Repair.class, id);
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        session.close();
        return repair;
    }

    /**
     * Get all repairs from database
     *
     * @return all repairs from database
     */
    public static List<Repair> getAll() {
        List<Repair> repairs = null;
        Session session = SessionService.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Repair");
            repairs = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        session.close();
        return repairs;
    }

    /**
     * Update repair in database
     *
     * @param repair in database
     */
    public static void update(Repair repair){
        Session session = SessionService.getSessionFactory().openSession();
        Transaction transaction = null;
        repair.setUpdatedAt(new Date());
        try {
            transaction = session.beginTransaction();
            session.update(repair);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        session.close();
    }

    /**
     * Delete repair in database
     *
     * @param id repair in database
     * @return true if removed repair in database, false if not removed repair to database
     */
    public static Boolean delete(Long id) {
        Boolean result = false;
        Session session = SessionService.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("delete from Repair where ID=:ID");
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
     * Check that the vehicle can be removed
     *
     * @param id of car in database
     * @return true if can, false if can't
     */
    public static Repair carCanDelete(Long id) {

        Repair repair = null;
        Session session = SessionService.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Repair where CAR_ID=:CAR_ID");
            query.setParameter("CAR_ID", id);
            query.setMaxResults(1);
            repair = (Repair) query.uniqueResult();
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        session.close();

        return repair;
    }

    /**
     * Sort repairs by created at descending
     * @param repairs
     * @return
     */
    public static List<Repair> sortCreatedAtDesc(List<Repair> repairs){
        Collections.sort(repairs, ComparatorService.CREATED_AT);
        return repairs;
    }
}
