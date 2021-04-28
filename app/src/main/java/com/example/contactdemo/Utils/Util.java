package com.example.contactdemo.Utils;

import android.app.Activity;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by User on 12/22/2017.
 */

public class Util {

    public static String loadJSONFromAsset(Activity activity,String jsonfile) {
        String json = null;
        try {
            InputStream is = activity.getAssets().open(jsonfile);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
