package com.example.t_tiger.helpmom;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
    private CardView tipsCard,dietCard,medicineCard,hospitalsCard;
    private Session session;
    private Button btnLogout;
    private Toolbar mTopToolbar;
    private String mLanguageCode = "bn";
    private String mLanguageCode2 = "en";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTopToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(mTopToolbar);


        session = new Session(this);
        if(!session.loggedin()){
            logout();
        }
        //btnLogout = (Button)findViewById(R.id.logout);
        /*btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });*/
        // Defining Cards
        tipsCard = (CardView) findViewById(R.id.tips_card);
        dietCard = (CardView) findViewById(R.id.diet_card);
        medicineCard = (CardView) findViewById(R.id.med_card);
        hospitalsCard = (CardView) findViewById(R.id.hospital_card);

        // Setting OnClickListener to Cards
        tipsCard.setOnClickListener(this);
        dietCard.setOnClickListener(this);
        medicineCard.setOnClickListener(this);
        hospitalsCard.setOnClickListener(this);




        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_home:
                        Intent intent = new Intent(SecondActivity.this, SecondActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        break;
                    case R.id.action_bmi:
                        Intent intent2 = new Intent(SecondActivity.this, Bmi.class);
                        startActivity(intent2);
                        break;
                    case R.id.action_calender:
                        Intent intent3 = new Intent(SecondActivity.this, Calender.class);
                        startActivity(intent3);
                        break;
                }
                    return true;
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbarnavigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.logout) {
           logout();
            return true;
        }
        if (id == R.id.action_english){
            LocaleHelper.setLocale(SecondActivity.this, mLanguageCode2);

            //It is required to recreate the activity to reflect the change in UI.
            restartActivity(SecondActivity.this);
            
            return true;
        }
        if (id == R.id.action_bangla){
            LocaleHelper.setLocale(SecondActivity.this, mLanguageCode);

            //It is required to recreate the activity to reflect the change in UI.
            restartActivity(SecondActivity.this);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        Intent i;

        switch(view.getId()){
            case R.id.tips_card :
                i = new Intent(this,Tips.class);
                startActivity(i);
                break;
            case R.id.diet_card :
                i = new Intent (this, Diet.class);
                startActivity(i);
                break;
            case R.id.med_card :
                i = new Intent (this, Medicine.class);
                startActivity(i);
                break;
            case R.id.hospital_card :

                i = new Intent (this, MapsActivity.class);
                startActivity(i);
                //Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                //startActivity(intent);
                break;
            default:
                break;
        }

    }
    private void logout(){
        session.setLoggedin(false);
        finish();
        startActivity(new Intent(SecondActivity.this,LoginActivity.class));
    }
    public static void restartActivity(Activity activity) {
        if (Build.VERSION.SDK_INT >= 11) {
            activity.recreate();
        } else {
            activity.finish();
            activity.startActivity(activity.getIntent());
        }
    }
}
