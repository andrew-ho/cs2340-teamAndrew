package model;

import android.widget.ImageView;


/**
 * @author team11
 * @version 1.0
 */

public class LostItem extends Item {


    /**
     * empty constructor required for firebase
     */
    public LostItem(){

    }

    /**
     * constructor chaining
     * @param name name of item
     * @param description description of item
     * @param key key of item
     * @param userName user who lost it
     */
    public LostItem(String name, String description, String key, String userName){
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
    public LostItem(String name, String description, String key,
                     String userName, ImageView image) {
        super(name,description,key,userName,image);
    }

}
