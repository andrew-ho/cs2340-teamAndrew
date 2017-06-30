package model;

import android.widget.ImageView;

import java.util.ArrayList;

/**
 * @author team11
 * @version 1.0
 */

public class LostItems {

    private static ArrayList<LostItems> masterItemList = new ArrayList<>();
    private String description;
    private ImageView picture;
    private String name;
    private ImageView defaultImage;
    private String key;
    private String userName;
    /**
     * gets username
     * @return username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * sets username
     * @param userName the username
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * gets key
     * @return key
     */
    public String getKey() {
        return key;
    }

    /**
     * sets key
     * @param key the key
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * gets name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * gets picture
     * @return picture
     */
    public ImageView getPicture(){
        return picture;
    }

    /**
     * gets decription
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * sets description
     * @param description description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * sets name
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * empty constructor required for firebase
     */
    /**
     * empty constructor required for firebase
     */
    public LostItems(){

    }

    /**
     * constructor chaining
     * @param name name of item
     * @param description description of item
     * @param key key of item
     * @param userName user who lost it
     */
    public LostItems(String name, String description, String key, String userName){
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
    public LostItems(String name, String description, String key,
                     String userName, ImageView image) {
        this.name = name;
        this.description = description;
        this.key = key;
        this.userName = userName;
        this.picture = image;
        //masterItemList.add(this);
    }


}
