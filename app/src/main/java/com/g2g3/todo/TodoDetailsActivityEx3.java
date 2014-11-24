package com.g2g3.todo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class TodoDetailsActivityEx3 extends Activity{

    private static final String TAG = "TodoDetailsActivityEx3";

	private Spinner mCategory;
    private EditText mEditText;
    private EditText mEditText2;

	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

        Log.d(TAG,"onCreate");

	    setContentView(R.layout.todo_detail);

        mEditText = (EditText) findViewById(R.id.todo_edit_summary);
        mEditText2 = (EditText) findViewById(R.id.todo_edit_description);
	    mCategory = (Spinner) findViewById(R.id.category);

	    Button confirmButton = (Button) findViewById(R.id.todo_edit_button);
	
	    
	    confirmButton.setOnClickListener(new View.OnClickListener() {
		      public void onClick(View view) {
                  Log.d(TAG, "onClick");

		  	    /**
		         * Fill this method
		         */

                  //get data
                  String c = mCategory.getSelectedItem().toString();
                  String t = mEditText.getText().toString();
                  String d = mEditText2.getText().toString();

                  Log.d(TAG, "cat="+c);
                  Log.d(TAG, "et="+t);


                  Intent i = new Intent("com.g2g3.todo.ACTION");

                  Bundle b = new Bundle();
                  b.putString("t",t);
                  b.putString("c",c);
                  b.putString("d",d);
                  i.putExtras(b);

                  LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(getBaseContext());
                  lbm.sendBroadcast(i);

                  setResult(RESULT_OK);

                  finish(); //backstack
		      }
		    });
	 }
}
