package com.rafistudio.csefaculty;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.rafistudio.csefaculty.R;

public class Developer extends AppCompatActivity {

    TextView DeveloperMail, DeveloperCall;
    String sNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.developer);
        getSupportActionBar().setTitle("Developer");

        DeveloperMail = findViewById(R.id.developerMail);
        DeveloperCall = findViewById(R.id.developerCall);
    }

    public void devcall(View v) {
        Intent i = new Intent(Intent.ACTION_DIAL);
        sNum = DeveloperCall.getText().toString();
        if (sNum.trim().isEmpty()) {
            i.setData(Uri.parse("tel:01515255863"));
        } else {
            i.setData(Uri.parse("tel:" + sNum));
        }
        startActivity(i);
    }

    public void devmail(View v) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setData(Uri.parse("email"));
        String[] s = {"sangitacse0@gmail.com"};
        i.putExtra(Intent.EXTRA_EMAIL, s);
        i.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        i.putExtra(Intent.EXTRA_TEXT, "Email Body");
        i.setType("message/rfc822");
        Intent chooser = Intent.createChooser(i, "Launch Email");
        startActivity(chooser);
    }
}
