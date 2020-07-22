package com.rafistudio.csefaculty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

import com.rafistudio.csefaculty.R;

public class TeacherList extends AppCompatActivity {

    private ListView t_listView;
    private TeacherAdapter teacherAdapter;
    private List<TeacherUnit> teacherUnits = new ArrayList<TeacherUnit>();
    private JSONArray jsonArray = null;
    public static final String JSON_ARRAY = "result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_list);

        t_listView = findViewById(R.id.listViewShowTeachersCCE);

        Intent intent = getIntent();
        final String t_dept = intent.getExtras().getString("t_dept");
        final String DepartmentName = intent.getExtras().getString("t_deptName");
        getSupportActionBar().setTitle(DepartmentName);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL.SHOW_TEACHERS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject jsonObject = null;
                        teacherUnits.clear();
                        try {
                            jsonObject = new JSONObject(response);
                            jsonArray = jsonObject.getJSONArray(JSON_ARRAY);
                            if (jsonArray.length() == 0) {
                                Toast.makeText(TeacherList.this, "No Data Recorded", Toast.LENGTH_SHORT).show();
                            } else {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    TeacherUnit teacherUnit = new TeacherUnit();

                                    teacherUnit.setT_name(object.getString("t_name"));
                                    teacherUnit.setT_post(object.getString("t_post"));
                                    teacherUnit.setT_dept(object.getString("t_dept"));
                                    teacherUnit.setT_faculty(object.getString("t_faculty"));
                                    teacherUnit.setT_email(object.getString("t_email"));
                                    teacherUnit.setT_contact(object.getString("t_contact"));

                                    teacherUnits.add(teacherUnit);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        teacherAdapter = new TeacherAdapter(TeacherList.this, teacherUnits);
                        t_listView.setAdapter(teacherAdapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(TeacherList.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("t_dept", t_dept);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(TeacherList.this);
        requestQueue.add(stringRequest);

        t_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(TeacherList.this, TeacherPersonalDetails.class);
                TeacherUnit me = teacherUnits.get(position);

                intent.putExtra("t_name", me.getT_name());
                intent.putExtra("t_post", me.getT_post());
                intent.putExtra("t_faculty", me.getT_faculty());
                intent.putExtra("t_dept", me.getT_dept());
                intent.putExtra("t_email", me.getT_email());
                intent.putExtra("t_contact", me.getT_contact());

                startActivity(intent);
            }
        });
    }
}