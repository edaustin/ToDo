package com.g2g3.todo;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class TodoDetailsActivityEx2_V2_bound extends Activity {

    private static final String TAG = "TodoDetailsActivity2";

	private Spinner mCategory;
	private EditText mEditText;

    private NoteEx2_V2_bound modelhandler;

    private boolean mBound=false;

	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
	    setContentView(R.layout.todo_detail);

        bindService(new Intent(this, NoteEx2_V2_bound.class), SC2, BIND_AUTO_CREATE);
	    
	    mEditText = (EditText) findViewById(R.id.todo_edit_summary);
	    mCategory = (Spinner) findViewById(R.id.category);

	    Button confirmButton = (Button) findViewById(R.id.todo_edit_button);

        confirmButton.setOnClickListener(new View.OnClickListener() {
		      public void onClick(View view) {
                  Log.d(TAG, "onClick");

		  	    /**
		         * Fill this method
		         */

                  //get data
                  String cat = mCategory.getSelectedItem().toString();
                  String et = mEditText.getText().toString();

                  Log.d(TAG, "cat="+cat);
                  Log.d(TAG, "et="+et);

                  modelhandler.modelHandler(cat,et);

                  finish(); //backstack no activity result
		      }
		    });
	 }

    @Override
    protected void onPause() {
        super.onPause();
        unbindService(SC2);
    }

    ServiceConnection SC2 = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

            //use service functions inside/outside the extends binder
            Log.d(TAG,"Component="+componentName);
            NoteEx2_V2_bound.EBS oBinder = (NoteEx2_V2_bound.EBS) iBinder;
            modelhandler = oBinder.mbs();

            mBound=true;

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

            mBound=false;
        }
    };
}
