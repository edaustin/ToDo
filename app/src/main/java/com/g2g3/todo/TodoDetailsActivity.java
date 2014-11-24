package com.g2g3.todo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class TodoDetailsActivity extends Activity{

    private static final String TAG = "TodoDetailsActivity";

	private Spinner mCategory;
	private EditText mEditText;

	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
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

                  Intent data = new Intent();
                  data.putExtra("cat",cat);
                  data.putExtra("et",et);

                  setResult(RESULT_OK, data);

                  finish(); //backstack
		      }
		    });
	 }
}
