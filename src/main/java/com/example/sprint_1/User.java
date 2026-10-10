package com.example.sprint_1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/*
--Attributes: username, fname, lname, age, gender, height, startWeight, targetWeight
            List<DailyLog>                  //DailyLog objects will contain daily activities.
            regDate(LocalDate), currWeight
Getters and setters for appropriate attributes.
-----------------Helper methods:
calculateBMI(weight, height): double
getDaysBet(from_LocalDate, to_LocalDate): int
getWkNum(from_LocalDate, to_LocalDate): int
addLog(entry: DailyLog): void           //Update currWeight here
 */
public class User {
//--------Attributes - immutable
    private final String userName;
    private final LocalDate regDate;

    //Attributes - mutable
    private String fName;                       //First name
    private String lName;                       //Last name
    private int age;
    private String gender;                      //Male, female, prefer-not-to-answer (ignore case)
    private double height;                      //inches

    private double startWeight;                 //Starting weight
    private double targetWeight;                //Goal

    List<DailyLog> logs = new ArrayList<>();        //List of daily entries

//----------Primary Constructor - takes regDate
    public User(String userName, LocalDate regDate, String fName, String lName, int age,
                    String gender, double height, double startWeight, double targetWeight)
    {
        this.userName = userName; this.regDate = regDate;       //Assign immutable

        setFName(fName); setLName(lName);
        setAge(age); setGender(gender); setHeight(height);

        setStartWeight(startWeight); setTargetWeight(targetWeight);

    }
//----------Secondary Constructor - sets regDate to the current day
    public User(String userName, String fName, String lName, int age,
                String gender, double height, double startWeight, double targetWeight)
    {
        this(userName, LocalDate.now() , fName, lName, age, gender,height ,startWeight , targetWeight);
    }

//------------------Setters
    public void setGender(String gender) {        //Gender name
        if(fName == null || !List.of("male", "female", "prefer not to answer").contains(fName.toLowerCase()))
            throw new IllegalArgumentException("Invalid gender option.");                                       //Throw exception if selection is out of scope.

        this.gender = gender;
    }

    public void setLName(String lName) {        //Last name
        if(lName==null || !lName.matches("[a-zA-Z\\-]+"))
            throw new IllegalArgumentException("Invalid last name entry.");

        this.lName = lName;
    }

    public void setFName(String fName) {        //Last name
        if(fName==null || !fName.matches("[a-zA-Z\\-]+"))
            throw new IllegalArgumentException("Invalid first name entry.");

        this.fName = fName;
    }





}
