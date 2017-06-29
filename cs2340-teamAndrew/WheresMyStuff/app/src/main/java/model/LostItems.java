package model;

import android.media.Image;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by Andrew Tuttle on 6/22/2017.
 * Models the items lost by our lovely users.
 */

public class LostItems {

    private static ArrayList<LostItems> masterItemList = new ArrayList<>();
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

    public LostItems(){

    }

    public LostItems(String name, String description, String key, String userName){
        this(name, description, key, userName, null);
    }

    public LostItems(String name, String description, String key,
                     String userName, ImageView image) {
        this.name = name;
        this.description = description;
        this.key = key;
        this.userName = userName;
        this.picture = image;
        //masterItemList.add(this);
    }

    public static ArrayList<LostItems> getItems() {
        return masterItemList;
    }

}
