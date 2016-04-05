package com.bleedev.mypkb;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by manoj.gupta on 4/5/2016.
 */
public class AppUtil {

    public static final String PREF = "MyPrefs";
    public static Map<Integer, String> template = new HashMap<Integer, String>() {
        {
            put(0, "Regards,\nManoj Gupta\n+91 9004175857"); // sign
            put(1, "Manoj Gupta"); // name
            put(2, "602, Rameshwaram Apmt, Opp. Keshav Nagar, Vaishali Nagar, NM Road, Dahisar(E), Mumbai-400068."); // address
            put(3, "09004175857"); //phone
            put(4, "manoj5115@gmail.com"); // email
            put(5, "Hi there,\n\nPls see attached CV.\n\nRegards,\n" +
                    "Manoj Gupta\n" +
                    "+91 9004175857"); // CV template

        }
    };

    public static void saveAll(Context ctx) {
        SharedPreferences sharedpreferences = ctx.getSharedPreferences(AppUtil.PREF, Context.MODE_PRIVATE);
        if(!sharedpreferences.getBoolean("firstLaunch", true)){
            return;
        }
        SharedPreferences.Editor editor = sharedpreferences.edit();

        for (Integer key : template.keySet()) {
            editor.putString(String.valueOf(key), template.get(key));
        }
        editor.putBoolean("firstLaunch", false);
        editor.commit();
    }

    public static void updateKeyText(Context ctx, int key, String val) {
        SharedPreferences sharedpreferences = ctx.getSharedPreferences(AppUtil.PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putString(String.valueOf(key), val);

        editor.commit();
    }

    public static String getKeyText(Context ctx, int key) {
        SharedPreferences sharedpreferences = ctx.getSharedPreferences(AppUtil.PREF, Context.MODE_PRIVATE);
        return sharedpreferences.getString(String.valueOf(key), null);

    }

}
