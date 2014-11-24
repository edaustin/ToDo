package com.g2g3.todo;

import android.app.ListActivity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class TodoOverviewActivityEx2_V2_bound extends ListActivity {

    private static final String TAG = "TodoOverviewActivity2";
    private static final int RQ = 1;

	public ArrayList<String> values;
	private SimpleArrayAdapter adapter;


    private NoteEx2_V2_bound modelhandler;

    private boolean mBound=false;

	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        bindService(new Intent(this, NoteEx2_V2_bound.class), SC, BIND_AUTO_CREATE);

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
        //startActivityForResult(new Intent(this, TodoDetailsActivityEx2_V2_bound.class), RQ);
        startActivity(new Intent(this, TodoDetailsActivityEx2_V2_bound.class));

    }

    @Override
    protected void onStart() {
        super.onStart();


    }


    @Override
    protected void onResume() {
        super.onResume();


        //method to receive from bound connection
        //yes, this is a kludge (timing) could do a lot more elegantly

        if (mBound) {
            Bundle b = modelhandler.returnModelHandler();
            adapter.addAll(b.getString("cat") + ":" + b.get("et"));

        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        unbindService(SC);
    }




    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG,"onActivityResult");

        String cat = (String) data.getExtras().get("cat");
        String et = (String) data.getExtras().get("et");

        Log.d(TAG, "cat="+cat);
        Log.d(TAG, "et="+et);

        /**
         * Fill this method
         */

        adapter.addAll(cat+":"+et);
    }

    ServiceConnection SC = new ServiceConnection() {
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
