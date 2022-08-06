package edu.ewubd.mybloodbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        findViewById(R.id.buttonSearch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this,edu.ewubd.mybloodbank.SearchActivity.class);
                startActivity(i);

            }
        });
        findViewById(R.id.buttonUpdate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, edu.ewubd.mybloodbank.UpdateProfile.class);
//                i.putExtra("bloodGroup",searchBG);
//                i.putExtra("divition",searchDivition);
                String DV;
                if (savedInstanceState == null) {
                    Bundle extras = getIntent().getExtras();
                    if(extras == null) {
                        DV= null;
                    } else {
                        DV= extras.getString("userid");
                    }
                } else {
                    DV= (String) savedInstanceState.getSerializable("userid");
                }
                i.putExtra("userid",DV);
                startActivity(i);

            }
        });
        findViewById(R.id.buttonAboutUs).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this,edu.ewubd.mybloodbank.AboutUs.class);
                startActivity(i);

            }
        });
        findViewById(R.id.buttonHealthTips).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this,edu.ewubd.mybloodbank.HealthTips.class);
                startActivity(i);

            }
        });
        findViewById(R.id.buttonHelp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this,edu.ewubd.mybloodbank. Help.class);
                startActivity(i);

            }
        });
        findViewById(R.id.buttonLogOut).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this,edu.ewubd.mybloodbank.SplashScreenActivity.class);
                startActivity(i);

            }
        });
    }
}