package com.example.zhangyuanke.istudyandroid;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangyuanke on 16/10/5.
 */

public class ActivityManager {
    public static List<Activity> activities = new ArrayList<Activity>();
    public static void addActivity(Activity activity)
    {
        activities.add(activity);
    }
    public static void removeActivity(Activity activity)
    {
        activities.remove(activity);
    }
    public static void finishAll(){
        for (Activity activity : activities)
        {
            if (!activity.isFinishing())
            {
                activity.finish();
            }
        }
    }
//    public static Activity currentActivity()
//    {
//        if (activities.size() > 0)
//        {
//            return activities.get(activities.size() - 1);
//        }
//        return null;
//    }
}
