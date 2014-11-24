package com.g2g3.todo;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class TodoOverviewActivity extends ListActivity {

    private static final String TAG = "TodoOverviewActivity";
    private static final int RQ = 1;

	public ArrayList<String> values;
	private SimpleArrayAdapter adapter;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		values = new ArrayList<String>();
		values.add("Important: Do the dinner");
		values.add("Reminder: Ironing"); 
		
		adapter = new SimpleArrayAdapter(this, values);
	    setListAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		String item = (String) getListAdapter().getItem(position);
		Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.listmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	
    	switch (item.getItemId()) {
		case R.id.insert:
            Log.d(TAG,"onOptionsItemSelected switch="+item);
			/* 
			 * Create a new method to call a new activity to show the TodoDetailActivity 
			 * */
			createTodo();
			return true;
		}
        return super.onOptionsItemSelected(item);
    }
    
    /**
     * Fill this method
     */
    private void createTodo() {
        Log.d(TAG,"createTodo");
        startActivityForResult(new Intent(this, TodoDetailsActivity.class), RQ);
    	
    	
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG,"onActivityResult");

        if(resultCode == Activity.RESULT_OK) {


            String cat = (String) data.getExtras().get("cat");
            String et = (String) data.getExtras().get("et");

            Log.d(TAG, "cat=" + cat);
            Log.d(TAG, "et=" + et);

            /**
             * Fill this method
             */

            adapter.addAll(cat + ":" + et);
        }
    }

}
