package com.rafistudio.csefaculty;

import android.content.Intent;
import android.os.Bundle;
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

import androidx.appcompat.app.AppCompatActivity;

public class StudentRegistration extends AppCompatActivity {


    EditText student_name, id_no, reg_number, password, session, phone, st_eamil;
    Spinner s_batch_spinner;
    Button SignUp;
    private String name, Id_no, reg_no, pass, sessioN, batch, contact, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_registration);
        getSupportActionBar().setTitle("Student Registration");

        SignUp = findViewById(R.id.SignUp_student);
        student_name = findViewById(R.id.name_student);
        id_no = findViewById(R.id.id_student);
        reg_number = findViewById(R.id.regNo_student);
        password = findViewById(R.id.pass_student);
        session = findViewById(R.id.Session_student);
        phone = findViewById(R.id.phone_student);
        st_eamil = findViewById(R.id.email_student);
        s_batch_spinner = findViewById(R.id.batch_spinner_student);

        ArrayAdapter BATCH_adapter = ArrayAdapter.createFromResource(StudentRegistration.this, R.array.batch, android.R.layout.simple_spinner_dropdown_item);
        BATCH_adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        s_batch_spinner.setAdapter(BATCH_adapter);

        s_batch_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                batch = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(StudentRegistration.this, "Please Select One...", Toast.LENGTH_SHORT).show();
            }
        });

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (student_name.getText().toString().equals("") &&
                        id_no.getText().toString().equals("") &&
                        reg_number.getText().toString().equals("") &&
                        password.getText().toString().equals("") &&
                        session.getText().toString().equals("") &&
                        phone.getText().toString().equals("") &&
                        st_eamil.getText().toString().equals("")) {

                    Toast.makeText(StudentRegistration.this, "please fill all the fields...", Toast.LENGTH_SHORT).show();
                } else {

                    name = student_name.getText().toString();
                    Id_no = id_no.getText().toString();
                    reg_no = reg_number.getText().toString();
                    pass = password.getText().toString();
                    sessioN = session.getText().toString();
                    contact = phone.getText().toString();
                    email = st_eamil.getText().toString();

                    RequestQueue requestQueue = Volley.newRequestQueue(StudentRegistration.this);
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, URL.STUDENT_REGISTRATION,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    if (response.equals("Success")) {
                                        Intent intent = new Intent(StudentRegistration.this, MainActivity.class);
                                        Toast.makeText(StudentRegistration.this, "success", Toast.LENGTH_SHORT).show();
                                        startActivity(intent);
                                        finish();
                                    } else {

                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(StudentRegistration.this, error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("name", name);
                            params.put("Id_no", Id_no);
                            params.put("reg_no", reg_no);
                            params.put("pass", pass);
                            params.put("sessioN", sessioN);
                            params.put("batch", batch);
                            params.put("contact", contact);
                            params.put("email", email);
                            return params;
                        }
                    };
                    requestQueue.add(stringRequest);
                }
            }
        });

    }
}