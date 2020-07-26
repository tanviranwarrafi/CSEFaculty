package com.rafistudio.csefaculty;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Button DeanButton, teachersCSE, studentsCSE, developer, loginButton;
    TextView stu_reg;

    EditText loginUserName, loginPassword;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Student Login");

        DeanButton = findViewById(R.id.din);
        teachersCSE = findViewById(R.id.teachers);
        studentsCSE = findViewById(R.id.s_students);
        developer = findViewById(R.id.developer);
        stu_reg = findViewById(R.id.student_registration);
        loginUserName = findViewById(R.id.s_id);
        loginPassword = findViewById(R.id.s_pass);
        loginButton = findViewById(R.id.login_student_button);
        pd = new ProgressDialog(MainActivity.this);

        DeanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Dean.class);
                startActivity(intent);
            }
        });

        teachersCSE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Department.class);
                startActivity(intent);
            }
        });

        studentsCSE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ShowStudents.class);
                startActivity(intent);
            }
        });

        developer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Developer.class);
                startActivity(intent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginRequest();
            }
        });

        stu_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), StudentRegistration.class));
            }
        });
    }

    private void loginRequest() {

        if (loginUserName.getText().toString().equals("") &&
                loginPassword.getText().toString().equals("")) {
            Toast.makeText(MainActivity.this, "please enter valid username and password", Toast.LENGTH_SHORT).show();
        } else {

            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL.STUDENT_LOGIN,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                pd.hide();
                                JSONObject jsonObject = new JSONObject(response);
                                String Response = jsonObject.getString("response");
                                Log.d("response", "login: " + Response);

                                if (Response.equals("success")){
                                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(MainActivity.this, StudentPersonalDetails.class);
                                    intent.putExtra("Id_no", loginUserName.getText().toString());
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(MainActivity.this, "Invalid id or password", Toast.LENGTH_SHORT).show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                                Log.d("response", "error: " + e.getMessage());
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            pd.hide();
                            Toast.makeText(MainActivity.this, "Check your internet connection", Toast.LENGTH_SHORT).show();
                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("Id_no", loginUserName.getText().toString());
                    params.put("pass", loginPassword.getText().toString());
                    return params;
                }
            };

            RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
            queue.add(stringRequest);
        }

    }
}