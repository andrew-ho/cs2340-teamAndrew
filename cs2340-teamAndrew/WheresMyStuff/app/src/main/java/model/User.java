package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * Created by Andrew Tuttle.
 */

public class User {
    final private String username;
    final private String password;
    protected String type;
  
    final private static Collection<User> masterList = new ArrayList<>();
    final private ArrayList<LostItem> myLostItems = new ArrayList<>();

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
