package com.g2g3.todo;

import android.app.ListActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class TodoOverviewActivityEx3 extends ListActivity {

    private static final String TAG = "TodoOverviewActivityEx3";
    private static final int RQ = 1;

    CustomArrayAdapter mAdapter;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        // no setContentView when extending ListActivity

        LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(this);
        lbm.registerReceiver(BR, new IntentFilter("com.g2g3.todo.ACTION"));

        //set up null
        ArrayList<ToDo> arrayOfToDos = new ArrayList<ToDo>();
        mAdapter = new CustomArrayAdapter(this, arrayOfToDos);
        setListAdapter(mAdapter);

        //initially populate items using the mandated Constructor method as per requirements
        NoteEx3 NX = new NoteEx3("Title A","Important","Description A", this);

        //add a second using a standard method
        NX.setData("Title B","Reminder","Description B", this);

	}


	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		ToDo item = (ToDo) getListAdapter().getItem(position);
		Toast.makeText(this, item.description + " selected", Toast.LENGTH_LONG).show();
	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.listmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	Log.d(TAG,"onOptionsItemSelected");
    	switch (item.getItemId()) {
		case R.id.insert:
            Log.d(TAG,"onOptionsItemSelected switch="+item);
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
        startActivityForResult(new Intent(this, TodoDetailsActivityEx3.class), RQ);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	super.onActivityResult(requestCode, resultCode, data);
        //disregard as we reimplemented as above and no longer require a passd intent
        Log.d(TAG,"onActivityResult");

    }



    BroadcastReceiver BR = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            ArrayList<ToDo> arrayOfToDos = new ArrayList<ToDo>();

            Bundle extras = intent.getExtras();
            //API12
            String t = extras.getString("t", "");
            String c = extras.getString("c", "");
            String d = extras.getString("d", "");

            Log.d(TAG,"BR "+t+"/"+c+"/"+d);

            ToDo td = new ToDo(t,c,d);
            arrayOfToDos.add(td);

            mAdapter.addAll(arrayOfToDos);

        }
    };

}
