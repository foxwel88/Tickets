package edu.nju.tickets.util;

/**
 * Created by foxwel on 2018/3/15.
 */
public class LevelUtil {

    public static double getCount(int level) {
        if (level == 1) return 1;
        if (level == 2) return 0.98;

        if (level == 3) return 0.95;

        if (level >=4) return 0.9;
        else return 1;
    }

    public static int getLevel(int price) {
        if (price > 5000) return 2;
        return 1;
    }
}
