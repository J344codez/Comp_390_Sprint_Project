/*
Edits:
Date        Name        Edits
====        ====        ========================================
10/11       Mo          Created class.
                        Added calsBurned overloaded methods and delegation method using log instance.


 */


package com.example.sprint_1;

public final class Calculator {

    private Calculator() {}             //Avoid instantiation, Calculator object not needed

//-------------------Utilites--------------calc = shortened calculate----------------------------
    //BMI = (lbs*703) / inches(height)^2
    public static double calcBmi(double weight, double height) {
        if (height < 1) throw new IllegalArgumentException("Invalid height.");
        return (weight*703) / Math.pow(height, 2);
    }

    //----Calories burned from just steps and no workout -- Weight-adjusted
    public static int calcCalsBurned(int steps, double weight) {       //cals = steps * (lbs * 0.00025)
        return (int) (steps * (weight * 0.00025));
    }

    //Overloaded calsBurned using steps and activity
    public static int calcCalsBurned(DailyLog.Activity activity, int duration,
                                         int steps, double weight) {
        //Steps = MET * weight (kg) * duration (hours)
        double met = getMet(activity);
        int woSteps = estSteps(activity, duration);
        double weight_kg = weight / 2.20462;        //Convert lbs to kg
        double duration_hr = duration / 60.0;         //Convert mins to hrs

        int calsFromWo = (int) (met * weight_kg * duration_hr);      //Burned from activity

        int remainingSteps = Math.max(0, steps-woSteps);        //Get remaining steps
        int calsFromSteps = calcCalsBurned(remainingSteps, weight); //Burned from steps

        return calsFromSteps + calsFromWo;              //Return total burned.

    }


//-------------------Helper Methods-----------------------------------------------------------------------------

    //Estimate MET based on activity
    public static double getMet(DailyLog.Activity activity) {
        double met = 0;
        switch (activity) {
            case DailyLog.Activity.CARDIO:
                met = 8.0;
                break;
            case DailyLog.Activity.CYCLING:
                met = 6.0;
                break;
            case DailyLog.Activity.WEIGHTS:
                met = 3.5;
                break;
        }
        return met;
    }

    //Estimate steps based on activity
    public static int estSteps(DailyLog.Activity activity, int duration) {
        int rate = 0;            //steps/min
        switch (activity) {
            case DailyLog.Activity.CARDIO:
                rate = 130;
                break;
            case DailyLog.Activity.WEIGHTS:
                rate = 25;
                break;
        }   //CYCLING = 0

        return rate * duration;         //rate * mins = estimated_steps
    }

    //Method to decide which calcCalsBurned to use - given log data
    public int calcCalsBurned(DailyLog log) {
        if (log.getWoDuration() != null && log.getWoDuration() > 0) {
            //cals burned = steps + workout
            return calcCalsBurned(log.getWorkout(), log.getWoDuration(),log.getSteps(), log.getWeight());
        }
        //cals burned from steps only
        return calcCalsBurned(log.getSteps(), log.getWeight());
    }

}
