package Database.Models;

import Database.Services.DatabaseOption;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: 30.08.2016
 * Time: 15:48
 */
public class Database {
    String title;

    String description;

    DatabaseOption option;

    public Database(String title, String description, DatabaseOption option){
        this.title = title;
        this.description = description;
        this.option = option;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DatabaseOption getOption() {
        return option;
    }

    public void setOption(DatabaseOption option) {
        this.option = option;
    }
}
