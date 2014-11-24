package com.g2g3.todo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SimpleArrayAdapter extends ArrayAdapter<String>{

    private static final String TAG = "SimpleArrayAdapter";

	private final Context context;
	private final ArrayList<String> values;
	
	public SimpleArrayAdapter(Context context, ArrayList<String> values) {
			super(context, R.layout.rowlayout, values);
			this.context = context;
			this.values = values;
	}
	
	
	@Override
	public View getView(int position, View contentView, ViewGroup parent) {
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View rv = inflater.inflate(R.layout.rowlayout, parent, false);
		
		TextView tv = (TextView) rv.findViewById(R.id.label);
		ImageView iv = (ImageView) rv.findViewById(R.id.icon);
		
		tv.setText(values.get(position));
		
		String s = values.get(position);
		
		if(s.contains("Reminder")){
			iv.setImageResource(R.drawable.reminder);
		}
		else if (s.contains("Important")) {
			iv.setImageResource(R.drawable.important);
		}
		else {
			iv.setImageResource(R.drawable.todo);
		}
		return rv;
	}

}
