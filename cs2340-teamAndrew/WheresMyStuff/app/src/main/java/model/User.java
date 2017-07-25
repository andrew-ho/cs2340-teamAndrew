package model;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Created by Andrew Tuttle.
 */

public class User {
    private final String username;
    private final String password;
    protected String type;
  
    private static final Collection<User> masterList = new ArrayList<>();
    // --Commented out by Inspection (7/25/2017 1:48 PM):private final ArrayList<LostItem> myLostItems = new ArrayList<>();


    /**
     * constructor for user
     * @param username username of user
     * @param password password of user
     */
    public User(String username, String password) {
        this.password = password;
        this.username = username;
        this.type = "User";
        masterList.add(this);
    }


    public String toString() {
        return username + " " + password + " " + type;
    }

}
