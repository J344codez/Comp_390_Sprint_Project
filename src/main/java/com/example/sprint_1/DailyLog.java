package com.example.sprint_1;

/*
Edits:
Date        Name        Edits
====        ====        ========================================
10/9        Mo          Created class, defined compareTo method


 */

import java.time.LocalDate;

public class DailyLog implements Comparable<DailyLog> {

    public enum Activity {RUNNING, CARDIO, WEIGHTS}
//---------------Attributes of a log--------------------
    private final User user;

    private LocalDate date;
    private double weight;          //lbs

    private int steps;                      //total steps

    private int calsBurned;
    private int calsConsumed;
    private double bmi;

    //----Optional fields
    private Activity workout;               //enum options
    private Integer workoutDuration;            //in mins












    //---------------------------------------------------------------------------
    @Override
    public int compareTo(DailyLog o) {                      //method from Comparable, needed for sorting
      return  this.getDate().compareTo(o.getDate());
    }



}
