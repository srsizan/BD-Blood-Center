package edu.ewubd.mybloodbank;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.LocalDate;
import java.util.Date;

import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SearchResult extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        Database db = new Database(this);
        TextView textView = findViewById(R.id.searchResult1);
        StringBuilder sb = new StringBuilder();
        String BG;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                BG= null;
            } else {
                BG= extras.getString("bloodGroup");
            }
        } else {
            BG= (String) savedInstanceState.getSerializable("bloodGroup");
        }
        String DV;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                DV= null;
            } else {
                DV= extras.getString("divition");
            }
        } else {
            DV= (String) savedInstanceState.getSerializable("divition");
        }


        Cursor cursor = db.searchResult(BG,DV);
        while (cursor.moveToNext()){


                sb.append("Name: " + cursor.getString(2) + "\nEmail Address:  " + cursor.getString(3) + "\nPhone Number:  " + cursor.getString(4) + "\nAddress :" + cursor.getString(6) + "\n\n");

        }
        textView.setText(sb);

        findViewById(R.id.resultsearch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(SearchResult.this, SearchActivity.class);
                startActivity(i);
            }
        });

        findViewById(R.id.homeresult).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(SearchResult.this, HomeActivity.class);
                startActivity(i);
            }
        });

    }
}