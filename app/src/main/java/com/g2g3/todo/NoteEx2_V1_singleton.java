package com.g2g3.todo;

import android.util.Log;

/**
 * Created by ed on 24/11/14.
 */

public class NoteEx2_V1_singleton {

    private static final String TAG = "NoteEx2_V1_singleton";


        private static NoteEx2_V1_singleton instance;

        //Globals
        private String cat;
        private String et;

        //non instantiable only factory
        private NoteEx2_V1_singleton(){}

        public void setData(String c, String e){
            Log.d(TAG,"setData "+c+" "+e);
            this.cat=c;
            this.et=e;
        }
    public String getDataCat(){

        Log.d(TAG,"getData "+this.cat+" "+this.et);

        return this.cat;
    }

    public String getDataEt(){

        Log.d(TAG,"getData "+this.cat+" "+this.et);

        return this.et;
    }



        public static synchronized NoteEx2_V1_singleton getInstance(){
            if(instance==null){
                instance=new NoteEx2_V1_singleton();
            }
            return instance;
        }
    }

