package com.example.textreader;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class textView extends AppCompatActivity {
    TextView TVdata;
    String value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view);
        TVdata=findViewById(R.id.TVdata);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            value = extras.getString("data");
            //The key argument here must match that used in the other activity
        }
        else{
            Toast.makeText(this, "The database is null", Toast.LENGTH_SHORT).show();
        }
        TVdata.setText(value);
    }
}