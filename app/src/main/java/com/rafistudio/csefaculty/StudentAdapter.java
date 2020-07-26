package com.rafistudio.csefaculty;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;


public class StudentAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<StudentUnit> studentUnits;

    public StudentAdapter(Activity activity, List<StudentUnit> studentUnits) {
        this.activity = activity;
        this.studentUnits = studentUnits;
    }

    @Override
    public int getCount() {
        return studentUnits.size();
    }

    @Override
    public Object getItem(int position) {
        return studentUnits.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null) {
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.student_list_item_layout, null);
        }
        StudentUnit su = studentUnits.get(position);
        TextView name = convertView.findViewById(R.id.tvName);
        name.setText(su.getName());

        return convertView;
    }
}