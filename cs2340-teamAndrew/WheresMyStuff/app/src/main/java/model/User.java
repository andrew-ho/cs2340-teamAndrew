package model;

import java.util.ArrayList;

/**
 * Created by andre on 6/14/2017.
 */

public class User {
    private String username;
    private String password;

    private static ArrayList<User> masterList = new ArrayList<>();

    public User(String username, String password) {
        this.password = password;
        this.username = username;
        masterList.add(this);
    }

    public static boolean validate(String username, String password) {
        for (User user : masterList) {
            if (user.username.equals(username) && user.password.equals(password)) {
                return true;
            }
        }
        return false;
    }

}
