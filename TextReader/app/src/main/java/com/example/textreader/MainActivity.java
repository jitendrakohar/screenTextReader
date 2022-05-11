package com.example.textreader;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button button;
    public int flag=1;
    CardView cardView1,cardView2;
    ImageButton imageButton;
    DBHelper db;
    DBHelperinstagram dbi;

    ImageButton IBinstagram;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.BPermission);
        cardView1=findViewById(R.id.cardView);
        cardView2=findViewById(R.id.cardView2);
        imageButton=findViewById(R.id.imgbutton);
        IBinstagram=findViewById(R.id.imgbutton2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS));
                Toast.makeText(MainActivity.this, "Turn on accessibility mode for this app: AccessibilityServicesExamples", Toast.LENGTH_SHORT).show();
            }
        });
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db=new DBHelper(MainActivity.this,"whatsapp");
                Cursor res=db.getData();

                if(res.getCount()==0){
                    Toast.makeText(MainActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while(res.moveToNext()){
                    buffer.append(""+res.getString(0)+"\n\n");
//                    Toast.makeText(MainActivity.this, "string"+buffer.toString(), Toast.LENGTH_SHORT).show();

                }

//                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
//                builder.setCancelable(true);
//                builder.setTitle("User Entries");
//                builder.setMessage(buffer.toString());
//                builder.show();
                startActivity(new Intent(MainActivity.this, textView.class).putExtra("data",buffer.toString()));

            }
        });
        IBinstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
//                builder.setCancelable(true);
//                builder.setTitle("User Entries");
//                builder.setMessage("Do you want to display the message here");
//                builder.show();

                dbi=new DBHelperinstagram(MainActivity.this,"instagram");
                Cursor res=dbi.getData();

                if(res.getCount()==0){
                    Toast.makeText(MainActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while(res.moveToNext()){
                    buffer.append(""+res.getString(0)+"\n\n");
//                    Toast.makeText(MainActivity.this, "string"+buffer.toString(), Toast.LENGTH_SHORT).show();

                }

                startActivity(new Intent(MainActivity.this, textView.class).putExtra("data",buffer.toString()));
            }
        });

    }
}