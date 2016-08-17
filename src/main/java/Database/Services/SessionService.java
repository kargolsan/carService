package Database.Services;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: 17.08.2016
 * Time: 12:16
 */
public class SessionService {

    /** Session factory to connect with database */
    private static SessionFactory sessionFactory;

    /** Service registry */
    private static ServiceRegistry serviceRegistry;

    /**
     * Get session factory to connection with database
     *
     * @return session factory
     */
    public static SessionFactory getSessionFactory() {
        try {
            if (sessionFactory == null) {
                SessionFactoryBuild();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return sessionFactory;
    }

    /**
     * Close opened session factory
     */
    public static void close() {
        try {
            if (sessionFactory != null){
                sessionFactory.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Build the session factory
     */
    private static void SessionFactoryBuild()
    {
        try {
            Configuration configuration = ConfigurationService.getConfigurationForHibernate();

            serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}