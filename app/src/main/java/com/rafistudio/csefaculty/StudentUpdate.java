package com.rafistudio.csefaculty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import androidx.appcompat.app.AppCompatActivity;

import com.rafistudio.csefaculty.R;

public class StudentUpdate extends AppCompatActivity {

    private EditText student_name, passStudent, phone, email_up;
    private Button updateStudent;
    private String name, pass, contact, gotStudentId, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_update);
        getSupportActionBar().setTitle("Student Update");

        Intent intent = getIntent();
        gotStudentId = intent.getExtras().getString("Id_no");

        updateStudent = findViewById(R.id.PersonalUpdateStudent);
        student_name = findViewById(R.id.personalName_update);
        passStudent = findViewById(R.id.personalPass_update);
        phone = findViewById(R.id.PersonalContact_update);
        email_up = findViewById(R.id.PersonalEmail_update);

        updateStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = student_name.getText().toString();
                contact = phone.getText().toString();
                pass = passStudent.getText().toString();
                email = email_up.getText().toString();

                RequestQueue requestQueue = Volley.newRequestQueue(StudentUpdate.this);
                StringRequest stringRequest = new StringRequest(Request.Method.POST, URL.STUDENT_UPDATE,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(StudentUpdate.this, response, Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(StudentUpdate.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("name", name);
                        params.put("Id_no", gotStudentId);
                        params.put("contact", contact);
                        params.put("pass", pass);
                        params.put("email", email);
                        return params;
                    }
                };
                requestQueue.add(stringRequest);
                Intent in = new Intent(StudentUpdate.this, MainActivity.class);
                startActivity(in);
            }
        });
    }
}
