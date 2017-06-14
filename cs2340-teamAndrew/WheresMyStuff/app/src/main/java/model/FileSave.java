package model;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
/**
 * Created by Andrew on 6/14/2017.
 */

public class FileSave {

    public static void loadUsers() throws FileNotFoundException {
        ArrayList<User> loadUsers = User.getUsers();
        PrintWriter pw = new PrintWriter("Users.txt");
        for (User user: loadUsers) {
            pw.println(user);
        }
        pw.close();
    }
}
