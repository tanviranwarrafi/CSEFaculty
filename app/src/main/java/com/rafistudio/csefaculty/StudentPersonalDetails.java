package com.rafistudio.csefaculty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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

import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

import com.rafistudio.csefaculty.R;

public class StudentPersonalDetails extends AppCompatActivity {

    private TextView up_name, up_id, up_reg, up_pass, up_batch, up_session, up_contact, up_email;
    private String name, id, reg, pass, batch, session, contact, LogingetId, email;
    private Button update_btn;
    private JSONArray jsonArray = null;
    public static final String JSON_ARRAY = "result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_personal_details);
        getSupportActionBar().setTitle("Student Detail");

        up_name = findViewById(R.id.personal_name);
        up_id = findViewById(R.id.personal_id);
        up_reg = findViewById(R.id.personal_reg);
        up_pass = findViewById(R.id.personal_pass);
        up_batch = findViewById(R.id.personal_batch);
        up_session = findViewById(R.id.personal_session);
        up_contact = findViewById(R.id.personal_contact);
        up_email = findViewById(R.id.personal_email);
        update_btn = findViewById(R.id.personalStudentUpdate);

        Intent intent = getIntent();
        LogingetId = intent.getExtras().getString("Id_no");

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST, URL.STUDENT_DETAILS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(response);
                    jsonArray = jsonObject.getJSONArray(JSON_ARRAY);
                    if (jsonArray.length() == 0) {
                        Toast.makeText(StudentPersonalDetails.this, "No Data Recorded", Toast.LENGTH_SHORT).show();
                    } else {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            StudentUnit stdUnit = new StudentUnit();

                            stdUnit.setName(object.getString("name"));
                            stdUnit.setId_no(object.getString("Id_no"));
                            stdUnit.setReg_no(object.getString("reg_no"));
                            stdUnit.setPass(object.getString("pass"));
                            stdUnit.setBatch(object.getString("batch"));
                            stdUnit.setSessioN(object.getString("sessioN"));
                            stdUnit.setContact_no(object.getString("contact"));
                            stdUnit.setEmail(object.getString("email"));

                            name = stdUnit.getName();
                            id = stdUnit.getId_no();
                            reg = stdUnit.getReg_no();
                            pass = stdUnit.getPass();
                            batch = stdUnit.getBatch();
                            contact = stdUnit.getContact_no();
                            session = stdUnit.getSessioN();
                            email = stdUnit.getEmail();

                            up_name.setText(name);
                            up_id.setText(id);
                            up_reg.setText(reg);
                            up_pass.setText(pass);
                            up_batch.setText(batch);
                            up_session.setText(session);
                            up_contact.setText(contact);
                            up_email.setText(email);
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Id_no", LogingetId);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(StudentPersonalDetails.this);
        requestQueue.add(stringRequest);

        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(StudentPersonalDetails.this, StudentUpdate.class);

                i.putExtra("name", up_name.getText().toString());
                i.putExtra("Id_no", up_id.getText().toString());
                i.putExtra("reg_no", up_reg.getText().toString());
                i.putExtra("pass", up_pass.getText().toString());
                i.putExtra("batch", up_batch.getText().toString());
                i.putExtra("contact", up_contact.getText().toString());
                i.putExtra("sessioN", up_session.getText().toString());
                i.putExtra("email", up_email.getText().toString());

                startActivity(i);
            }
        });
    }
}