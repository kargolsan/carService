package Database.Services;

import javafx.util.Callback;
import Database.Models.Database;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: 17.08.2016
 * Time: 17:14
 */
public class CellFactoryService {

    /**
     * Factory value for title
     *
     * @return value factory
     */
    public Callback<ListView<Database>,ListCell<Database>> propertyTitleFactory(){
        return new Callback<ListView<Database>,ListCell<Database>>(){
            @Override
            public ListCell<Database> call(ListView<Database> p) {
                return new ListCell<Database>(){
                    @Override
                    protected void updateItem(Database database, boolean bln) {
                        super.updateItem(database, bln);
                        if(database != null){
                            setText(database.getTitle());
                        }else{
                            setText(null);
                        }
                    }
                };
            }
        };
    }
}
