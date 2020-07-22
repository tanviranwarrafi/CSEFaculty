package com.rafistudio.csefaculty;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;


public class TeacherAdapter extends BaseAdapter {

    private Activity activityt;
    private LayoutInflater layoutInflater;
    private List<TeacherUnit> teacherUnits;

    public TeacherAdapter(Activity activityt, List<TeacherUnit> teacherUnits) {
        this.activityt = activityt;
        this.teacherUnits = teacherUnits;
    }

    @Override
    public int getCount() {
        return teacherUnits.size();
    }

    @Override
    public Object getItem(int position) {
        return teacherUnits.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (layoutInflater == null) {
            layoutInflater = (LayoutInflater) activityt.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.teacher_list_item_layout, null);
        }

        TeacherUnit su = teacherUnits.get(position);
        TextView name = (TextView) convertView.findViewById(R.id.teacher_name);
        name.setText(su.getT_name());

        return convertView;
    }
}
