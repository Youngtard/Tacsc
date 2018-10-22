package com.youngtard.tacsc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class TimelineActivity extends AppCompatActivity {
    Button reportAnIssue;
    FirebaseAuth auth;
    TextView userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        getSupportActionBar().setTitle("Timeline");

        userId = findViewById(R.id.tv_userId);

        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        userId.setText(user.getPhoneNumber());



        reportAnIssue = findViewById(R.id.btn_report_an_issue);

        reportAnIssue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivityForResult();
//                Intent intent = new Intent(TimelineActivity.this, IssueRep
                Intent intent = new Intent(TimelineActivity.this, BrandsActivity.class);
//                intent.putExtra()
                startActivity(intent);
            }
        });
    }


//    @Override
//    public void onBackPressed() {
//
//        super.onBackPressed();
//        finish();
//
//    }
}
