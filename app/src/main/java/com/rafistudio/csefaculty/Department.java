package com.rafistudio.csefaculty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.rafistudio.csefaculty.R;

public class Department extends AppCompatActivity {

    private Button cce, cit, eee, pme, mat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.department);
        getSupportActionBar().setTitle("Department");

        cce = findViewById(R.id.cce);
        cit = findViewById(R.id.cit);
        eee = findViewById(R.id.eee);
        pme = findViewById(R.id.pme);
        mat = findViewById(R.id.mat);

        cit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Department.this, TeacherList.class);
                i.putExtra("t_dept", "Department of Computer Science and Information Technology");
                i.putExtra("t_deptName", "Department of CIT");
                startActivity(i);
            }
        });

        cce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Department.this, TeacherList.class);
                i.putExtra("t_dept", "Department of Computer and Communication Engineering");
                i.putExtra("t_deptName", "Department of CCE");
                startActivity(i);
            }
        });

        eee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Department.this, TeacherList.class);
                i.putExtra("t_dept", "Department of Electricl and Electronics Engineering");
                i.putExtra("t_deptName", "Department of EEE");
                startActivity(i);
            }
        });

        pme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Department.this, TeacherList.class);
                i.putExtra("t_dept", "Department of Physics and Mechanical Engineering");
                i.putExtra("t_deptName", "Department of PME");
                startActivity(i);
            }
        });

        mat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Department.this, TeacherList.class);
                i.putExtra("t_dept", "Department of Mathematics");
                i.putExtra("t_deptName", "Department of Mathematics");
                startActivity(i);
            }
        });

    }
}


