package com.rafistudio.csefaculty;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.rafistudio.csefaculty.R;

public class StudentDetails extends AppCompatActivity {

    private TextView st_name, st_id, st_reg, st_batch, st_session, st_contact, st_email;
    private String name, id, reg, batch, sessioN, contact, email;
    private Button btnCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_details);
        getSupportActionBar().setTitle("Student Details");

        st_name = findViewById(R.id.st_name);
        st_id = findViewById(R.id.st_id);
        st_reg = findViewById(R.id.st_reg);
        st_batch = findViewById(R.id.st_batch);
        st_session = findViewById(R.id.st_session);
        st_contact = findViewById(R.id.st_contact);
        st_email = findViewById(R.id.st_email);

        btnCall = findViewById(R.id.btnCall);

        Intent intent = getIntent();
        name = intent.getExtras().getString("name");
        id = intent.getExtras().getString("Id_no");
        reg = intent.getExtras().getString("reg_no");
        batch = intent.getExtras().getString("batch");
        sessioN = intent.getExtras().getString("sessioN");
        contact = intent.getExtras().getString("contact");
        email = intent.getExtras().getString("email");

        st_name.setText(name);
        st_id.setText(id);
        st_reg.setText(reg);
        st_batch.setText(batch);
        st_session.setText(sessioN);
        st_contact.setText(contact);
        st_email.setText(email);

        btnCall.setText("Call " + name);
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + contact)));
            }
        });
    }
}