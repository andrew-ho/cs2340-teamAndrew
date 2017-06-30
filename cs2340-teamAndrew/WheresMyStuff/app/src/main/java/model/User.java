package model;

import android.content.Context;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;


/**
 * Created by andre on 6/14/2017.
 */

public class User {
    private String username;
    private String password;
    protected String type;
  
    private static ArrayList<User> masterList = new ArrayList<User>();
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
