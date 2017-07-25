package model;

/**
 * @author team11
 * @version 1.0
 */

public class FoundItem extends Item{

    /**
     * empty constructor required for firebase
     */
    public FoundItem(){

    }

    /**
     * constructor chaining
     * @param name name of item
     * @param description description of item
     * @param key key of item
     * @param userName user who lost it
     */
    public FoundItem(String name, String description, String key,
                     String userName) {
        super(name,description,key,userName);
        //masterItemList.add(this);
    }
}
