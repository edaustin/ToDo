package com.g2g3.todo;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomArrayAdapter extends ArrayAdapter<ToDo> {
    public CustomArrayAdapter(Context context, ArrayList<ToDo> td) {
        super(context, 0, td);
    }

    private static final String TAG = "CustomArrayAdapter";


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
        ToDo p = getItem(position);
        Log.d(TAG,p.title+"/"+p.category+"/"+p.description);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.rowfllnotelayout, parent, false);
        }


        // Lookup view for data population
        ImageView iv = (ImageView) convertView.findViewById(R.id.icon);
        TextView tv1 = (TextView) convertView.findViewById(R.id.lvtext1);
        TextView tv2 = (TextView) convertView.findViewById(R.id.lvtext2);


        if(p.category.equals("Reminder")){
            iv.setImageResource(R.drawable.reminder);
        }
        else if (p.category.equals("Important")) {
            iv.setImageResource(R.drawable.important);
        }
        else {
            iv.setImageResource(R.drawable.todo);
        }

        tv1.setText(p.title);
        tv2.setText(p.description);

        // Return the completed view to render on screen
        return convertView;
    }
}