package model;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by andre on 6/14/2017.
 */

public class User {
    private String username;
    private String password;
    protected String type;
  
    private static List<User> masterList = new ArrayList<>();
    private ArrayList<LostItem> myLostItems = new ArrayList<>();

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
