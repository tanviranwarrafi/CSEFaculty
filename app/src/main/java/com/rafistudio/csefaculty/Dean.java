package com.rafistudio.csefaculty;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Dean extends AppCompatActivity {

    TextView CallDeansir, MailDeansir;
    String sNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dean);
        getSupportActionBar().setSubtitle("Chief of CSE faculty");

        CallDeansir = findViewById(R.id.DeansirContact);
        MailDeansir = findViewById(R.id.DeansirEmail);
    }

    public void callDeansir(View v) {
        Intent i = new Intent(Intent.ACTION_DIAL);

        sNum = CallDeansir.getText().toString();
        if (sNum.trim().isEmpty()) {
            i.setData(Uri.parse("tel:01865102400"));
        } else {
            i.setData(Uri.parse("tel:" + sNum));
        }
        startActivity(i);
    }

    public void deansirEmail(View v) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setData(Uri.parse("email"));
        String[] s = {"jamalpstu07@yahoo.com"};
        i.putExtra(Intent.EXTRA_EMAIL, s);
        i.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        i.putExtra(Intent.EXTRA_TEXT, "Email Body");
        i.setType("message/rfc822");
        Intent chooser = Intent.createChooser(i, "Launch Email");
        startActivity(chooser);
    }
}




