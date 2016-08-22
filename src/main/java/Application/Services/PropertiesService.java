package Application.Services;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: 22.08.2016
 * Time: 16:44
 */
public class PropertiesService {

    /**
     * Path to properties of application
     */
    private static final String APPLICATION = "Application/Resources/Properties/application.properties";

    /**
     * Get property of application
     *
     * @param key in properties file
     * @return value of key
     */
    public static String getApplication(String key) {
        Properties prop = new Properties();
        try {
            prop.load(PropertiesService.class.getClassLoader().getResourceAsStream(APPLICATION));
            return prop.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
            return key;
        }
    }
}
