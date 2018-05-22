package com.hb.fatsecret.model;

public class Purpose {
    int goal;
    double targetWeight;
    int gender;
    int activityLevel;
    double currentWeight;
    double height;
    String dateOfBirth;
    String region;

    public Purpose(int goal, double targetWeight, int gender, int activityLevel,
                   double currentWeight, double height, String dateOfBirth, String region) {
        this.goal = goal;
        this.targetWeight = targetWeight;
        this.gender = gender;
        this.activityLevel = activityLevel;
        this.currentWeight = currentWeight;
        this.height = height;
        this.dateOfBirth = dateOfBirth;
        this.region = region;
    }

    public Purpose() {
    }

    public int getGoal() {
        return goal;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    public double getTargetWeight() {
        return targetWeight;
    }

    public void setTargetWeight(double targetWeight) {
        this.targetWeight = targetWeight;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(int activityLevel) {
        this.activityLevel = activityLevel;
    }

    public double getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(double currentWeight) {
        this.currentWeight = currentWeight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
