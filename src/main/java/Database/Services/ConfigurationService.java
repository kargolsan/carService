package Database.Services;

import Application.Services.AlertService;
import Modules.Cars.Models.Car;
import Modules.Repairs.Models.Part;
import Modules.Repairs.Models.Repair;
import Modules.Repairs.Models.Service;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.io.File;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.logging.LogManager;


/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: 17.08.2016
 * Time: 12:07
 */
public class ConfigurationService {

    private static String databaseName = "database";
    private static String serverPort = "7777";
    private static String databaseUser = "CarService";
    private static String databasePassword = "CarService";
    private static String serverIp = "";

    /**
     * Set database option;
     */
    private static DatabaseOption option;

    public static DatabaseOption getOption() {
        return option;
    }

    public static void setOption(DatabaseOption option) {
        ConfigurationService.option = option;
    }

    public static void setServerIp(String serverIp) {
        ConfigurationService.serverIp = serverIp;
    }

    /**
     * Get configuration database for hibernate
     *
     * @return configuration for hibernate
     */
    public static Configuration getConfigurationForHibernate()
    {
        String connectionUrl = "";
        String hbm2dll = "";

        switch (option) {
            case LOCAL:
                connectionUrl = String.format("jdbc:hsqldb:file:database/%1$s;shutdown=true;user=%2$s;password=%3$s", databaseName, databaseUser, databasePassword);
                hbm2dll = "update";
                break;

            case SERVER:
                ServerService.run();
                hbm2dll = "update";
                connectionUrl = String.format("jdbc:hsqldb:hsql://localhost:%1$s/%2$s", serverPort, databaseName);
                break;

            case CLIENT:
                hbm2dll = "validate";
                connectionUrl = String.format("jdbc:hsqldb:hsql://%1$s:%2$s/%3$s", serverIp, serverPort, databaseName);
                break;
        }

        return new org.hibernate.cfg.Configuration()
                .setProperty("hibernate.connection.driver_class", "org.hsqldb.jdbcDriver")
                .setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect")
                .setProperty("hibernate.connection.url", connectionUrl)
                .setProperty("hibernate.connection.username", databaseUser)
                .setProperty("hibernate.connection.password", databasePassword)
                .setProperty("hibernate.show_sql", "false")
                .setProperty("hibernate.hbm2ddl.auto", hbm2dll)
                .setProperty("hibernate.current_session_context_class", "thread")

                .setProperty("connection.provider_class", "org.hibernate.connection.C3P0ConnectionProvider")
                .setProperty("hibernate.c3p0.acquire_increment", "1")
                .setProperty("hibernate.c3p0.idle_test_period", "60")
                .setProperty("hibernate.c3p0.min_size", "1")
                .setProperty("hibernate.c3p0.max_size", "2")
                .setProperty("hibernate.c3p0.max_statements", "50")
                .setProperty("hibernate.c3p0.timeout", "5")
                .setProperty("hibernate.c3p0.acquireRetryAttempts", "0")
                .setProperty("hibernate.c3p0.acquireRetryDelay", "250")
                .setProperty("hibernate.use_sql_comments", "true")
                .setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false")
                .setProperty("hibernate.jdbc.lob.non_contextual_creation", "true")
                .setProperty("hibernate.transaction.coordinator_class", "org.hibernate.transaction.JDBCTransactionFactory")

                .setProperty("cache.provider_class", "org.hibernate.cache.NoCacheProvider")

                .addAnnotatedClass(Car.class)
                .addAnnotatedClass(Repair.class)
                .addAnnotatedClass(Part.class)
                .addAnnotatedClass(Service.class);
    }

    /**
     * Get localization of database local
     *
     * @return localization directory of database
     */
    public static String getLocalizationLocalDatabase(){
        return String.format("%1$s%2$sdatabase", System.getProperty("user.dir"), File.separator);
    }

    public static Properties buildPropertiesServer(){
        Properties prop = new Properties();
        System.out.println(getLocalizationLocalDatabase());
        prop.setProperty("server.database.0", String.format("file:%1$s/database;user=%2$s;password=%3$s", getLocalizationLocalDatabase(), databaseUser, databasePassword));
        prop.setProperty("server.dbname.0", databaseName);
        prop.setProperty("server.port", serverPort);
        prop.setProperty("server.remote_open", "true");
        prop.setProperty("hsqldb.reconfig_logging", "false");
        return prop;
    }

    public static void checkConnectionRemote() throws Exception {
            switch (option) {
                case CLIENT:
                    Connection c3 = DriverManager.getConnection(String.format("jdbc:hsqldb:hsql://%1$s:%2$s/%3$s", serverIp, serverPort, databaseName), databaseUser, databasePassword);
                    c3.close();
                    break;
            }
    }

    /**
     * Check exception contains information
     * about lock file of database
     *
     * @param error exception to check
     * @return true if file of database is locking or false if it is not locking
     */
    public static Boolean hasUnknownHostException(Exception error) {
        try {
            UnknownHostException exception = (UnknownHostException) error.getCause().getCause();
            //exception.getSQLState().contains("S1000");
            return true;
        } catch (Exception e){
            return false;
        }

    }
}
