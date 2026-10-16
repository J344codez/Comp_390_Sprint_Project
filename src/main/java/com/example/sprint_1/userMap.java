/*

Edits:
Date        Name        Edits
====        ====        =====
10/15       James       Created userMap class, added fields, get/set methods

*/

package com.example.sprint_1;
import java.util.*;

public class userMap {      
// ----------------- fields --------------------------------
    private String userName;                // userName (the key in k,v pair)
    private User user;                      // user obj (the value in k, v pair), holds user data (logs)
    private Map<String,User> userProfile;   // individual user profile(map), <userName, [userdata]>

    // arraylist to hold every user's data (array of user maps)
    private ArrayList<Map<String,User>> usersList = new ArrayList<>();

    // test/placeholder profile
    User testUser = new User("user203", "john", "smith", 27, "male", 86.5, 165.0, 145.0);

// --------------------constructor---------------------------
    public userMap(HashMap<String, User> userProfile){
        
        this.userProfile = userProfile;

        setUserMap(user);
        setUserName(userName);
    }

//----------------------- setters-----------------------------
    public void setUserMap(User user){      // creates new user map than adds to userslist
    /*  make map w/ k,v vals of <userName(str), user(User)>
        then add that map to array usersList*/

        // need way to handle duplicates -- shouldnt allow two profiles w/ same username
        try{
            userProfile.put(userName, user);
        } catch (Exception e) {System.out.println("That username is already taken");}

        usersList.add(userProfile);  

    }
    public void setUserName(String userName){    // possibly redundant? similar method in filehandler.java
        // get userName from User obj
        this.userName = user.getUserName();
    }

// -----------------------getters------------------------------
    public String getUserName(User user){
        // 
        return this.userName;
    }
    public Map<String,User> getUserMap(Map<String,User> userProfile){
        //
        return this.userProfile;
    }
    // get list of ALL user profiles (testing purpose only)
    public ArrayList<Map<String,User>> getUsersData(Map<String,User> usersList){ 
        
        return this.usersList;
    }
}

/* 
public Map<String,User> userSearch(ArrayList<Map<String,User>> usersList){
    // search usersList objs, at each obj check if userName field matches what
    // method is searching for 

    for (Map in:usersList){

    }
}
*/
