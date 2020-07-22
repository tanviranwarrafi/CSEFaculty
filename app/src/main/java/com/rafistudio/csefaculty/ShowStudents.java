package com.rafistudio.csefaculty;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
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

public class ShowStudents extends AppCompatActivity {

    Spinner batch_spinner;
    private ListView listView;
    private StudentAdapter studentAdapter;
    private List<StudentUnit> studentUnitList = new ArrayList<StudentUnit>();
    private String batch;
    private Button button;
    private JSONArray jsonArray = null;
    public static final String JSON_ARRAY = "result";
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_students);
        getSupportActionBar().setTitle("CSE Student List");

        button = findViewById(R.id.request_button);
        batch_spinner = findViewById(R.id.batch_spinner);
        listView = findViewById(R.id.listViewShowStudents);

        ArrayAdapter BATCH_adapter = ArrayAdapter.createFromResource(ShowStudents.this, R.array.batch, android.R.layout.simple_spinner_dropdown_item);
        BATCH_adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        batch_spinner.setAdapter(BATCH_adapter);

        batch_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                batch = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(ShowStudents.this, "Please Select One...", Toast.LENGTH_SHORT).show();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog = new ProgressDialog(ShowStudents.this);
                progressDialog.setMessage("Loading...");
                progressDialog.show();
                progressDialog.setCancelable(true);

                StringRequest stringRequest = new StringRequest(Request.Method.POST, URL.SHOW_STUDENTS,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                progressDialog.dismiss();
                                JSONObject jsonObject = null;
                                studentUnitList.clear();
                                try {
                                    jsonObject = new JSONObject(response);
                                    jsonArray = jsonObject.getJSONArray(JSON_ARRAY);
                                    if (jsonArray.length() == 0) {
                                        Toast.makeText(ShowStudents.this, "No Data Recorded", Toast.LENGTH_SHORT).show();
                                    } else {
                                        for (int i = 0; i < jsonArray.length(); i++) {
                                            JSONObject object = jsonArray.getJSONObject(i);
                                            StudentUnit stdUnit = new StudentUnit();

                                            stdUnit.setName(object.getString("name"));
                                            stdUnit.setId_no(object.getString("Id_no"));
                                            stdUnit.setReg_no(object.getString("reg_no"));
                                            stdUnit.setPass(object.getString("pass"));
                                            stdUnit.setSessioN(object.getString("sessioN"));
                                            stdUnit.setBatch(object.getString("batch"));
                                            stdUnit.setContact_no(object.getString("contact"));
                                            stdUnit.setEmail(object.getString("email"));

                                            studentUnitList.add(stdUnit);
                                        }
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                studentAdapter = new StudentAdapter(ShowStudents.this, studentUnitList);
                                listView.setAdapter(studentAdapter);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ShowStudents.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("batch", batch);
                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(ShowStudents.this);
                requestQueue.add(stringRequest);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StudentUnit st = studentUnitList.get(position);
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + st.getContact_no())));
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ShowStudents.this, StudentDetails.class);
                StudentUnit me = studentUnitList.get(position);

                intent.putExtra("name", me.getName());
                intent.putExtra("Id_no", me.getId_no());
                intent.putExtra("reg_no", me.getReg_no());
                intent.putExtra("pass", me.getPass());
                intent.putExtra("sessioN", me.getSessioN());
                intent.putExtra("batch", me.getBatch());
                intent.putExtra("contact", me.getContact_no());
                intent.putExtra("email", me.getEmail());

                startActivity(intent);
            }
        });
    }
}
