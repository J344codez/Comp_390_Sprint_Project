package com.example.sprint_1;

/*
Edits:
Date        Name        Edits
====        ====        ========================================
10/10       Mo          Created class, defined compareTo method
10/11       Mo          Added accessors/mutators
                        Added helper method to offload calculations to Calculator
10/17       Mo          Added toString


 */

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DailyLog implements Comparable<DailyLog> {

    public enum Activity {CARDIO, CYCLING, WEIGHTS}
    //---------------Attributes of a log--------------------
    private final User user;

    private LocalDate date;
    private double weight;          //lbs

    private int steps;                      //total steps
    private int calsConsumed;

    //----Optional fields
    private Activity workout;               //enum options
    private Integer workoutDuration;            //in mins

    //Calculate fields
    private int calsBurned;
    private double bmi;

    //----------Primary Constructor - takes optional fields
    public DailyLog(User user, LocalDate date, double weight, int steps,
                    int calsIntake, Activity workout, Integer woDuration) {
        //Required fields
        if (user == null) throw new IllegalArgumentException("Invalid user.");
        this.user = user;
        setLogDate(date);
        setLogWeight(weight); setLogSteps(steps); setLogCalsConsumed(calsIntake);
        //Optional fields
        setActivity(workout); setWoDuration(woDuration);
        //Calculate BMI and calsBurned with given data.
        reCal();
    }
    //----------Secondary Constructor - sets optional fields to null
    public DailyLog(User user, LocalDate date, double weight, int steps,
                    int calsIntake) {
        this(user, date, weight, steps, calsIntake, null, null);
    }

//---------------------------Setters------------------------------------------------------------

    public void setLogDate(LocalDate date) {
        if (date == null) throw new IllegalArgumentException("Invalid date entered.");
        this.date = date;
    }
    public void setLogWeight(double weight) {
        if(weight <= 0)                            //Check for 0 or negative entries
            throw new IllegalArgumentException("Weight must be positive.");
        this.weight = weight;
    }
    public void setLogSteps(int steps) {
        if(steps <= 0)                            //Check for 0 or negative entries
            throw new IllegalArgumentException("Steps must be positive.");
        this.steps = steps;
    }
    public void setLogCalsConsumed(int calsIntake) {
        if(calsIntake < 0)                            //Check for negative entries
            throw new IllegalArgumentException("Calories cannot be a negative value.");
        this.calsConsumed = calsIntake;
    }
    public void setActivity(Activity workout) {
        this.workout = workout;
    }
    public void setWoDuration(Integer woDuration) {
        if(woDuration != null && woDuration <= 0)           //Check for negative entries
            throw new IllegalArgumentException("Workout duration greater than 0.");
        this.workoutDuration = woDuration;
    }
    //-----calculated values
    public void setCalsBurned(int calsBurned) {
        if(calsBurned < 0)                            //Check for negative entries
            throw new IllegalArgumentException("Calories cannot be a negative value.");
        this.calsBurned = calsBurned;
    }
    public void setBmi(double bmi) {
        if(bmi < 0)                            //Check for negative entries
            throw new IllegalArgumentException("BMI cannot be a negative value.");
        this.bmi = bmi;
    }

//------------------Getters------------------------------------------------------------------------

    public User getUser() {return user; }
    public LocalDate getDate() {return date; }

    public double getWeight() {return weight; }
    public int getSteps() {return steps; }
    public int getCalsConsumed() {return this.calsConsumed; }

    public Activity getWorkout() { return this.workout; }
    public Integer getWoDuration() {return this.workoutDuration; }

    public int getCalsBurned() {return this.calsBurned; }
    public double getBmi() {return this.bmi; }

//------------------Helper methods-----------------------------------------------

    public void reCal() {           //Pass an instance of "this" to Calculator
        setBmi(Calculator.calcBmi(this.weight, this.user.getHeight()));                   //Calculate BMI
        setCalsBurned(Calculator.calcCalsBurned(this));     //Calculate calories burned
    }

    //-----------Concrete implementation of compareTo()-----------------------------------------------------------
    @Override
    public int compareTo(DailyLog o) {                //method from Comparable, needed for sorting
        return  this.getDate().compareTo(o.getDate());
    }


    //--toString for a single log
    public String toString() {
        String logDate = date.format(DateTimeFormatter.ofPattern("MM/dd/yy"));
        String wgt = String.format("%.1f", weight);
        String bmi = String.format("%.1f", this.bmi);
        //Date: |Weight(lbs): |Steps: | Calories Intake: Workout: |Duration: |Calories Burned: |BMI:
        StringBuilder log = new StringBuilder("Date: ").append(logDate).append(" | ");
        log.append("Weight(lbs): ").append(wgt).append(" | ");
        log.append("Steps: ").append(steps).append(" | ");
        log.append("Calories Intake: ").append(calsConsumed).append(" | ");
        //Optional fields
        if (workout != null) {
            int hrs = workoutDuration/60;
            int mins = workoutDuration%60;
            String d = String.format("%02d:%02d",hrs, mins);        //hh:mm

            log.append("Workout: ").append(workout).append(" | ");
            log.append("Duration(hh:mm): ").append(d).append(" | ");
        }
        //Cals burned & BMI
        log.append("Calories Burned: ").append(calsBurned).append(" | ");
        log.append("BMI: ").append(bmi);

        return log.toString();
    }

}
