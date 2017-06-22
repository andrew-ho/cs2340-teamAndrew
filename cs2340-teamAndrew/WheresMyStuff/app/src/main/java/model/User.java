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
    private String type;

    private static ArrayList<User> masterList = new ArrayList<User>();
    private ArrayList<LostItems> myLostItems = new ArrayList<>();

    public User(String username, String password, String type) {
        this.password = password;
        this.username = username;
        this.type = type;
        masterList.add(this);
    }

    public void addLostItem(String name, String description, ImageView image) {
        myLostItems.add(new LostItems(name,description,image));
    }

    public void addLostItem(String name, String description) {
        myLostItems.add(new LostItems(name, description));
    }

    public static void loadUsers(Context c) throws IOException {
        /*File f = new File("Users.txt");
        if (!f.exists()) {
            Toast.makeText(c.getApplicationContext(), "It does not exist", Toast.LENGTH_LONG).show();
            return;
        } else if (f.isFile()) {
            Scanner scan = new Scanner("Users.txt");
            while (scan.hasNext()) {
                User user = new User(scan.next(), scan.next());
            }
        }*/
        FileInputStream fis = c.openFileInput("Users.txt");
        InputStreamReader isr = new InputStreamReader(fis);
        Scanner scan = new Scanner(isr);
        while (scan.hasNext()) {
            User user = new User(scan.next(), scan.next(), scan.next());
        }

    }

    public static boolean validate(String username, String password) {
        for (User user : masterList) {
            if (user.username.equals(username) && user.password.equals(password)) {
                return true;
            }
        }
        return false;
    }

    public static boolean validate(String username) {
        for (User user: masterList) {
            if (user.username.equals(username)) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<User> getUsers() {
        return masterList;
    }

    public String toString() {
        return username + " " + password + " " + type;
    }

}
