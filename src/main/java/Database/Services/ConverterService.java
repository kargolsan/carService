package Database.Services;

import Database.Models.Database;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import javafx.util.StringConverter;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: 30.08.2016
 * Time: 16:47
 */
public class ConverterService {

    /**
     * Convert database to title for combo box
     *
     * @return value factory
     */
    public StringConverter<Database> convertDatabaseToTitle(){
        return new StringConverter<Database>() {
            @Override
            public String toString(Database database) {
                if (database == null){
                    return null;
                } else {
                    return database.getTitle();
                }
            }

            @Override
            public Database fromString(String title) {
                return null;
            }
        };
    }
}
