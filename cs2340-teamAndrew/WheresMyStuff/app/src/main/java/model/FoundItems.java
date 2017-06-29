package model;

import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by Alex on 6/29/2017.
 */

public class FoundItems {
    private static ArrayList<FoundItems> masterItemList = new ArrayList<>();
    private String description;
    private ImageView picture;
    private String name;
    private ImageView defaultImage;
    private String key;
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public ImageView getPicture(){
        return picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * empty constructor required for firebase
     */
    public FoundItems(){

    }

    /**
     * constructor chaining
     * @param name name of item
     * @param description description of item
     * @param key key of item
     * @param userName user who lost it
     */
    public FoundItems(String name, String description, String key, String userName){
        this(name, description, key, userName, null);
    }

    /**
     * constructor chaining
     * @param name name of item
     * @param description description of item
     * @param key key of item
     * @param userName user who lost it
     * @param image image of the item
     */
    public FoundItems(String name, String description, String key,
                     String userName, ImageView image) {
        this.name = name;
        this.description = description;
        this.key = key;
        this.userName = userName;
        this.picture = image;
        //masterItemList.add(this);
    }
}
