/*

Edits:
Date        Name        Edits
====        ====        =====
10/15       James       Created userMap class, added fields, get/set methods
10/20       James       Modified constructor to take User obj as parameter, removed unnecessary
                        /redundant variables/methods
10/21       James       Added methods getUser(), getuserName(), addUserName(), updateUserdata()

*/

package com.example.sprint_1;
import java.util.*;

// need to create, hold map + methods to access, add, delete
public class userMap {      // *** only going to create ONE instance, use across entire app
// ----------------- fields --------------------------------
    
    private Map<String,User> usersList;   // map holds data for all users

    // test/placeholder profile
    User testUser = new User("user203", "john", "smith", 27, 
                        "male", 86.5, 165.0, 145.0);

// --------------------constructor---------------------------
    public userMap(User user)
    {
        // needs to CREATE user map
        // take in user obj

        // setter methods called here

        addUser(user);
       
    }

    public userMap()    
    {
        // overloaded constructor, creates empty usersList (map)
        Map<String,User> usersList = new HashMap<String, User>();
    }

//----------------------- setters-----------------------------
    public void addUserName(String userName)    
    {
        // adds mapping for username w/ blank/none user val (like a blank/empty profile)
        try{
            usersList.put(userName, null);
        } catch (Exception e) {System.out.println("That username is already taken");}
        
    }

    public void addUser(User user)
    {      
        // adds user paired to map <str username, user user>
        // possible to add user (val) w/o accompanying username (key) but cant see a reason to
        // ie)  usersList.put(null, user);
    
        String userName = user.getUserName();

        // need way to handle duplicates -- shouldnt allow two profiles w/ same username
        try{
            usersList.put(userName, user);
        } catch (Exception e) {System.out.println("That username is already taken");}

    }

    // updateUserdata() --> finds blank username,user pairing and uses replace(k,v)
    // to add user data to that username
    public void updateUserdata(User user)
    {
        String userName = user.getUserName();

        User oldUser = getUser(userName);

        usersList.replace(userName, oldUser, user);
    }

// -----------------------getters------------------------------
    public String getUserName(User user){   // ****needs to be changed
        // use overloaded method to get username from user obj, OR use Un to return user obj
        String userName = user.getUserName();   // inherited/overloaded from User class
        return userName;
    }
    public User getUser(String userName){
        // return user data (user obj) based on username arg
        // manage exception if no user available to get - WIP

        User userProfile = usersList.get(userName);

        if(userProfile == null) {System.out.println("No user associated with that username");}

        return userProfile;
        
    }
    // get list of ALL user profiles (testing purpose only)
    public Map<String,User> getUsersData(Map<String,User> usersList){ 
        // for item in userslist, print item (?) WIP
        return this.usersList;
    }
// ------------------------ "if username/user exists" methods ---------------------------
    public boolean nameInMap(String userName)
    {
        // if username key in map, return true - else return false
        if (usersList.containsKey(userName))
        {
            return true;
        }
        else {return false;}
    }
    public boolean userInMap(User user)
    {
        // if user paired with username in map, return true - else return false
        if (usersList.containsValue(user))
        {
            return true;
        }
        else {return false;}
    }
// ------------------------------- misc methods ------------------------------------------
    public void removeUser(String userName)
    {
        // if username mapped to user obj (is in map), remove entry <str, user> from map
        if (nameInMap(userName))
        {
            usersList.remove(userName);
        }
        else {System.out.println("No user associated with that username");}
    }
}