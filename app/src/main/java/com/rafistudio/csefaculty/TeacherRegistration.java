package com.rafistudio.csefaculty;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.rafistudio.csefaculty.R;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class TeacherRegistration extends AppCompatActivity {

    EditText teacher_name, email, contact;
    Spinner post, faculty, dept;
    Button SignUp;
    private String t_name, t_post, t_faculty, t_dept, t_email, t_contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_registration);
        getSupportActionBar().setTitle("Teacher Registration");

        SignUp = findViewById(R.id.SignUp_teacher);
        teacher_name = findViewById(R.id.name_teacher);
        email = findViewById(R.id.email_teacher);
        contact = findViewById(R.id.contact_teacher);
        post = findViewById(R.id.post_teacher);
        dept = findViewById(R.id.dept_teacher);
        faculty = findViewById(R.id.faculty_teacher);

        ArrayAdapter POST_adapter = ArrayAdapter.createFromResource(TeacherRegistration.this, R.array.posts, android.R.layout.simple_spinner_dropdown_item);
        POST_adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        post.setAdapter(POST_adapter);

        post.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                t_post = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(TeacherRegistration.this, "Please Select One...", Toast.LENGTH_SHORT).show();
            }
        });

        ArrayAdapter FAC_adapter = ArrayAdapter.createFromResource(TeacherRegistration.this, R.array.facultys, android.R.layout.simple_spinner_dropdown_item);
        FAC_adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        faculty.setAdapter(FAC_adapter);

        faculty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                t_faculty = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(TeacherRegistration.this, "Please Select One...", Toast.LENGTH_SHORT).show();
            }
        });

        ArrayAdapter DEPT_adapter = ArrayAdapter.createFromResource(TeacherRegistration.this, R.array.departments, android.R.layout.simple_spinner_dropdown_item);
        DEPT_adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        dept.setAdapter(DEPT_adapter);

        dept.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                t_dept = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(TeacherRegistration.this, "Please Select One...", Toast.LENGTH_SHORT).show();
            }
        });

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (teacher_name.getText().toString().equals("") &&
                        email.getText().toString().equals("") &&
                        contact.getText().toString().equals("")) {

                    Toast.makeText(TeacherRegistration.this, "please fill all the fields...", Toast.LENGTH_SHORT).show();
                } else {
                    t_name = teacher_name.getText().toString();
                    t_email = email.getText().toString();
                    t_contact = contact.getText().toString();

                    RequestQueue requestQueue = Volley.newRequestQueue(TeacherRegistration.this);
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, URL.TEACHER_REGISTRATION,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Toast.makeText(TeacherRegistration.this, response, Toast.LENGTH_SHORT).show();
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(TeacherRegistration.this, error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<String, String>();

                            params.put("t_name", t_name);
                            params.put("t_post", t_post);
                            params.put("t_faculty", t_faculty);
                            params.put("t_dept", t_dept);
                            params.put("t_email", t_email);
                            params.put("t_contact", t_contact);

                            return params;
                        }
                    };
                    requestQueue.add(stringRequest);
                }
            }
        });
    }
}