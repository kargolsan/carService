package Database.Services;

import Modules.Cars.Models.Car;
import Modules.Repairs.Models.Part;
import Modules.Repairs.Models.Repair;
import Modules.Repairs.Models.Service;
import org.hibernate.cfg.Configuration;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: 17.08.2016
 * Time: 12:07
 */
public class ConfigurationService {

    /**
     * Get configuration database for hibernate
     *
     * @return configuration for hibernate
     */
    public static Configuration getConfigurationForHibernate()
    {
        return new org.hibernate.cfg.Configuration()
                .setProperty("hibernate.connection.driver_class", "org.hsqldb.jdbcDriver")
                .setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect")
                .setProperty("hibernate.connection.url", "jdbc:hsqldb:file:database/database;shutdown=true")
                .setProperty("hibernate.connection.username", "CarService")
                .setProperty("hibernate.connection.password", "CarService")
                .setProperty("hibernate.connection.pool_size", "5")
                .setProperty("hibernate.show_sq", "true")
                .setProperty("hibernate.hbm2ddl.auto", "update")
                .setProperty("hibernate.current_session_context_class", "thread")
                .setProperty("cache.provider_class", "org.hibernate.cache.NoCacheProvider")
                .addAnnotatedClass(Car.class)
                .addAnnotatedClass(Repair.class)
                .addAnnotatedClass(Part.class)
                .addAnnotatedClass(Service.class);
    }
}
