package com.g2g3.todo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class TodoDetailsActivityEx2_V1_singleton extends Activity{

    private static final String TAG = "TodoDetailsActivityEx2_V1_singleton";

	private Spinner mCategory;
	private EditText mEditText;

	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

        Log.d(TAG,"onCreate");

	    setContentView(R.layout.todo_detail);
	    
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

                  NoteEx2_V1_singleton g = NoteEx2_V1_singleton.getInstance();
                  g.setData(cat,et);

                  setResult(RESULT_OK);

                  finish(); //backstack
		      }
		    });
	 }
}
