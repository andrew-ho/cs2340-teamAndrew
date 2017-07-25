package model;

/**
 * Created by Andrew Tuttle.
 */

public class Admin extends User {

    /**
     * Constructor for Admins
     * @param username username of admin
     * @param password password of admin
     */
    public Admin(String username, String password) {
        super(username,password);
        this.type = "Admin";
    }

}
