package com.rafistudio.csefaculty;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.rafistudio.csefaculty.R;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TeacherPersonalDetails extends AppCompatActivity {

    private TextView teacher_name, teacher_post, teacher_dept, teacher_faculty, teacher_contact, teacher_email;
    private String t_name, t_post, t_faculty, t_dept, t_contact, t_email;
    private Button teacher_btnCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_personal_details);
        setTitle("Teacher Details");

        teacher_name = findViewById(R.id.t_personal_name);
        teacher_post = findViewById(R.id.teacher_personal_post);
        teacher_dept = findViewById(R.id.teacher_personal_department);
        teacher_faculty = findViewById(R.id.teacher_personal_faculty);
        teacher_contact = findViewById(R.id.teacher_personal_contact);
        teacher_email = findViewById(R.id.teacher_personal_email);
        teacher_btnCall = findViewById(R.id.teacher_personal_btnCall);

        Intent intent = getIntent();
        t_name = intent.getExtras().getString("t_name");
        t_post = intent.getExtras().getString("t_post");
        t_faculty = intent.getExtras().getString("t_faculty");
        t_dept = intent.getExtras().getString("t_dept");
        t_contact = intent.getExtras().getString("t_contact");
        t_email = intent.getExtras().getString("t_email");

        teacher_name.setText(t_name);
        teacher_post.setText(t_post);
        teacher_dept.setText(t_faculty);
        teacher_faculty.setText(t_dept);
        teacher_contact.setText(t_contact);
        teacher_email.setText(t_email);

        teacher_btnCall.setText("Call " + t_contact);

        teacher_btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + t_contact)));
            }
        });
    }
}
