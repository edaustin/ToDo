package com.g2g3.todo;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.Log;

/**
 * Created by ed on 24/11/14.
 */

public class Note extends Service {

    private static final String TAG = "MyBoundService";

    SharedPreferences preferences;


    private String c;
    private String e;

    public Note() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.

        preferences = PreferenceManager.getDefaultSharedPreferences(this);


        Log.d(TAG,"bound");
        return new EBS();
    }

    void modelHandler(String cat, String et){
        Log.d(TAG, "modelHandler()");
        Log.d(TAG, "cat="+cat);
        Log.d(TAG, "et="+et);


        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("cat", cat);
        editor.putString("et", et);
        editor.commit();

    }


    Bundle returnModelHandler(){

        String c = preferences.getString("cat", "err");
        String e = preferences.getString("et", "err");

        Bundle b = new Bundle();
        b.putString("cat", c);
        b.putString("et", e);

        return b;

    }





    public class EBS extends Binder {

        Note mbs(){
            return Note.this;
        }

    }
}