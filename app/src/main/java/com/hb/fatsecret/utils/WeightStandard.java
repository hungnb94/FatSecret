package com.hb.fatsecret.utils;

public class WeightStandard {

    public static int getBMIWeightType(double weight, double height, boolean isAsian) {
        double bmi = weight / (height * height);
        if (isAsian) {
            if (bmi < 18.5) return Constants.BMI_UNDERWEIGHT;
            if (bmi < 22.9) return Constants.BMI_NORMAL;
            if (bmi < 24.9) return Constants.BMI_OVERWEIGHT;
            if (bmi < 29.9) return Constants.BMI_OBESE_GRADE_1;
            if (bmi < 39.9) return Constants.BMI_OBESE_GRADE_2;
            return Constants.BMI_OBESE_GRADE_3;
        } else {
            if (bmi < 18.5) return Constants.BMI_UNDERWEIGHT;
            if (bmi < 24.9) return Constants.BMI_NORMAL;
            if (bmi < 29.9) return Constants.BMI_OVERWEIGHT;
            if (bmi < 34.9) return Constants.BMI_OBESE_GRADE_1;
            if (bmi < 39.9) return Constants.BMI_OBESE_GRADE_2;
            return Constants.BMI_OBESE_GRADE_3;
        }
    }
}
