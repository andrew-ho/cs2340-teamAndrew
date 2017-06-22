package model;

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


    public LostItems(String description) {
        this(description, null);
    }

    public LostItems(String description, ImageView image) {
        this.description = description;
        this.picture = image;
    }

}
