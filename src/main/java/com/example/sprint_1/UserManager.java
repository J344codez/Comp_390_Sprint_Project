/*
Description: Manages User operations and serves as the intermediary between the
                application and file I/O for read/write user data.

Edits:
Date        Name        Edits
====        ====        ========================================
10/12       Mo          Created class.
10/14       MO          Added some tentative classes - pending Map implementation.


 */

package com.example.sprint_1;

import java.util.Map;

public class UserManager {

    CustomMap<String, User> userMap = new CustomMap<>();             //Map to match username <-> User object


    public UserManager() {            //Will be filled in later


    }

//----------------------------------------------------------------------------------
    public boolean isUser(String username) {            //Check if this is an existing user
        if (username == null || username.isBlank()) throw new IllegalArgumentException("Username is empty.");       //Check for empty

        return (userMap.getUser(username) != null);
    }

    public User getUser(String username) {              //Return user if it exists, otherwise, prompt user to register.
        if (!isUser(username)) {
            throw new IllegalArgumentException("Username does not exist. Please create a new account.");
        }
        return userMap.getUser(username);
    }

    public void addUser(User user) {            //Add user to map
        if (user == null) throw new IllegalArgumentException("This is not a valid user.");

        userMap.add(user.getUserName(), user);      //Add user object to map
    }

    public void deleteUser(String username) {       //Delete user if user exists using the username
        if(!isUser(username)) throw new IllegalArgumentException("User does not exist.");

        userMap.remove(username);                   //Remove user
    }

    public void deleteUser(User user) {       //Delete user if user exists using the user object
        if(!isUser(user.getUserName())) throw new IllegalArgumentException("User does not exist.");

        userMap.remove(user.getUserName());                   //Remove user
    }

    public boolean addLogToUser (User user, DailyLog log) {
        if (user==null || log==null) throw new IllegalArgumentException("Invalid user or log.");

        user.addLog(log);                           //Add log to to the user's
    }




}
