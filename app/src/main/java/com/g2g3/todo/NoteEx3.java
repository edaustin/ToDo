package com.g2g3.todo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

/**
 * Created by ed on 24/11/14.
 */

public class NoteEx3 {

    private static final String TAG = "NoteEx3";

    public NoteEx3(String t, String c, String d, Context ctx){
            setData(t,c,d,ctx);
    }


    public void setData(String t, String c, String d, Context ctx){
            Log.d(TAG,"setData "+t+" "+c+" "+d);
            Bundle b = new Bundle();
            b.putString("t",t);
            b.putString("c",c);
            b.putString("d",d);
            brData(b, ctx);
    }


    private void brData(Bundle b, Context ctx){

            LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(ctx);

            lbm.sendBroadcast(new Intent("com.g2g3.todo.ACTION").putExtras(b));
    }


}

