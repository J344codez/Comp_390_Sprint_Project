package com.example.sprint_1;

import java.time.LocalDate;
import java.util.*;

/*
Edits:
Date        Name        Edits
====        ====        ========================================
10/9        Mo          Created class, added attributes
10/10       Mo          Added core structure - getters/setters, helper methods


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

    private double startWeight;                 //Starting weight (lbs)
    private double targetWeight;                //Goal
    private double currWeight;                  //Current weight

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
//---------------------------Setters-----------------------------------------------------------------------------------------------------------------------------------------------------------


    public void setGender(String gender) {        //Gender name
        if(fName == null || !List.of("male", "female", "prefer not to answer").contains(fName.toLowerCase()))
            throw new IllegalArgumentException("Invalid gender option.");                       //Throw exception if selection is out of scope.
        this.gender = gender;
    }

    public void setLName(String lName) {        //Last name
        if(lName == null || !lName.matches("[a-zA-Z\\-]+"))                                       //Only accept letters and -
            throw new IllegalArgumentException("Invalid last name entry.");
        this.lName = lName;
    }

    public void setFName(String fName) {        //Last name
        if(fName == null || !fName.matches("[a-zA-Z\\-]+"))
            throw new IllegalArgumentException("Invalid first name entry.");
        this.fName = fName;
    }

    public void setAge(int age) {
        if (age < 12)
            throw new IllegalArgumentException("Must be at least 12 years old.");
        this.age = age;
    }

    public void setHeight(double inches) {
        if(inches<36)                           //Implicit floor for height is 3ft
            throw new IllegalArgumentException("Invalid height entry.");
        this.height = inches;
    }

    public void setStartWeight(double lbs) {
        if(lbs <= 0)                            //Check for 0 or negative entries
            throw new IllegalArgumentException("Weight must be positive.");
        this.startWeight = lbs;
        this.currWeight = lbs;
    }

    public void setTargetWeight(double lbs) {
        if(lbs <= 0)                            //Check for 0 or negative entries
            throw new IllegalArgumentException("Weight must be positive.");
        this.targetWeight = lbs;
    }

    public void setCurrWeight(double lbs) {
        if(lbs <= 0)                            //Check for 0 or negative entries
            throw new IllegalArgumentException("Weight must be positive.");
        this.currWeight = lbs;
    }

//---------------------------Getters-----------------------------------------------------------------------------------------------------------------------------------------------------------

    public String getUserName() {return this.userName; }
    public LocalDate getRegDate() {return this.regDate; };

    public String fName() {return this.fName; }
    public String getlName() {return this.lName; }

    public String getGender() {return this.gender; }
    public int getAge() {return this.age; }
    public double getHeight() {return this.height; }
    public double getCurrWeight() {return this.currWeight; }

    public double getStartWeight() {return this.startWeight; }
    public double getTargetWeight() {return this.targetWeight; }

    public List<DailyLog> getLogs() {return this.logs; }        //Return a collection of the logs

    public List<DailyLog> getLogs(LocalDate date) {            //Return a collections of logs from given date
        List<DailyLog> targetLogs = new ArrayList<>();
        if (this.logs == null || this.logs.isEmpty())           //Check for null/empty
            return targetLogs;

        for (DailyLog log : this.logs) {                        //Add and return all logs for the given date
            if (log.getDate().equals(date))
                targetLogs.add(log);
        }
        return targetLogs;
    }

    public double getCurBMI() {                    //Return BMI using current weight
        return Calculator.calculateBMI(this.currWeight, this.height);
    }

//-------------------------Helper methods-------------------------------------------------------------------------------------------------------------------------------------------------------

    public void addLog(DailyLog log) {
        if(log == null)
            throw new IllegalArgumentException("Nothing to add.");

        this.logs.add(log);                             //Append the log list
        Collections.sort(this.logs);                    //sort by date
        this.currWeight = logs.getLast().getWeight();   //Update weight the most recent log
    }

    public void removeAllLogs() { this.logs.clear(); }      //Clear logs

    public boolean removeLog(LocalDate date) {              //Remove log for a specific date
        if (date == null) return false;                     //Null check
        boolean removed = false;

        Iterator<DailyLog> it = logs.iterator();            //Iterate through logs
        while (it.hasNext()) {
            DailyLog l = it.next();
            if (l.getDate().equals(date)) {                 //If log exists for given date - remove log(s)
                it.remove();
                removed = true;
            }
        }
        return removed;                                     //Return false if no log found for the date.
    }




}
