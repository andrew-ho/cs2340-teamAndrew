package model;

/**
 * Created by Andrew Tuttle on 6/29/2017.
 */

public class Admin extends User {

    /**
     * Constructor for Admins
     * @param username username of admin
     * @param password password of admin
     * @param type String detailing that this user is an Admin.
     */
    public Admin(String username, String password, String type) {
        super(username,password);
        this.type = "Admin";
    }

}
