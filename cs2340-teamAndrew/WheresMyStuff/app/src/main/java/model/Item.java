package model;

import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;


/**
 * Created by Andrew Tuttle.
 */

public abstract class Item {

    // --Commented out by Inspection (7/25/2017 1:47 PM):private static final ArrayList<Item> masterItemList = new ArrayList<>();
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
     * gets description
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
    public Item(){

    }

    public void moveIt(DatabaseReference foundRef, DatabaseReference ref) {
        foundRef.child(this.getKey()).setValue(this);
        ref.child(this.getKey()).removeValue();
    }

    public String toString() {
        String name = this.getName();
        String description = this.getDescription();
        String username = this.getUserName();
        String message = name + "\n" + description
                + "\n" + username + " has found this item!";
        return message;
    }

// --Commented out by Inspection START (7/25/2017 1:46 PM):
//    /**
//     * constructor chaining
//     * @param name name of item
//     * @param description description of item
//     * @param key key of item
//     * @param userName user who submitted the item
//     */
//    public Item(String name, String description, String key, String userName){
//        this(name, description, key, userName, null);
//    }
// --Commented out by Inspection STOP (7/25/2017 1:46 PM)

    /**
     * constructor chaining
     * @param name name of item
     * @param description description of item
     * @param key key of item
     * @param userName user who lost it
     * @param image image of the item
     */
    public Item(String name, String description, String key,
                     String userName, ImageView image) {
        this.name = name;
        this.description = description;
        this.key = key;
        this.userName = userName;
        this.picture = image;
        //masterItemList.add(this);
    }

}
