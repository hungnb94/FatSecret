package com.hb.fatsecret.utils;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;

public class Utility {

    /**
     * Generate a value suitable for use in { setId(int)}.
     * This value will not collide with ID values generated at build time by aapt for R.id.
     *
     * @return a generated ID value
     */
//    public static int generateViewId() {
//        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR1)
//            return View.generateViewId();
//        AtomicInteger sNextGeneratedId = new AtomicInteger(1);
//        for (; ; ) {
//            final int result = sNextGeneratedId.get();
//            // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
//            int newValue = result + 1;
//            if (newValue > 0x00FFFFFF) newValue = 1; // Roll over to 1, not 0.
//            if (sNextGeneratedId.compareAndSet(result, newValue)) {
//                return result;
//            }
//        }
//    }
    public static void setBackgroundDrawable(View view, Drawable drawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

    public static double changeLbToKg(double weight) {
        return weight * Constants.LB_TO_KG;
    }

    public static double changeInToCm(double height) {
        return height * Constants.IN_TO_CM;
    }

    public static double changeFtToCm(double height) {
        return height * Constants.FT_TO_CM;
    }
}
