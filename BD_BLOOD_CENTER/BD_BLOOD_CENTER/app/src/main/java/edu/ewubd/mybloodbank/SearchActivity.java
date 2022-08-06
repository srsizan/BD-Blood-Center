package edu.ewubd.mybloodbank;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SearchActivity extends AppCompatActivity {
    private  EditText searchBGTF,searchAreaTF;
    Database g = new Database(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        SQLiteDatabase db = g.getReadableDatabase();

        searchBGTF = findViewById(R.id.etbg);
        searchAreaTF = findViewById(R.id.etsv);
        Button backbtn=(Button)findViewById(R.id.backsearch);

        findViewById(R.id.btnsearch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchBG = searchBGTF.getText().toString();
                String searchDivition = searchAreaTF.getText().toString();

                if(searchDivition.isEmpty()){
                    //errormsg += "Please enter the Divition";
                    Toast.makeText(SearchActivity.this,"Enter the Divition",Toast.LENGTH_SHORT).show();
                }
                else if(searchBG.length() < 2|| searchBG.length()>3){
                    Toast.makeText(SearchActivity.this,"Invalid Blood Group",Toast.LENGTH_SHORT).show();
                }
                else{
                    Cursor cursor = g.searchResult(searchBG,searchDivition);
                    if(cursor.getCount()>0){
                        Intent i = new Intent(SearchActivity.this,SearchResult.class);
                        i.putExtra("bloodGroup",searchBG);
                        i.putExtra("divition",searchDivition);

                        startActivity(i);
                    }else{
                        Toast.makeText(SearchActivity.this,"blood donor is not availavle",Toast.LENGTH_LONG).show();
                    }



//                    Intent i = new Intent(SearchActivity.this,SearchResult.class);
//                        i.putExtra("bloodGroup",searchBG);
//                        i.putExtra("divition",searchDivition);
//
//                        startActivity(i);


                }


            }
        });
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(SearchActivity.this, edu.ewubd.mybloodbank.HomeActivity.class);
                startActivity(i1);

            }
        });
    }
}