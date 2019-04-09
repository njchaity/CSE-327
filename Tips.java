package helpmom;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.Dot;

public class Tips extends AppCompatActivity {

    private ViewPager mViewPager;
    private LinearLayout mDotLayout;
    private TextView[] Dots;
    private SlideAdapter slideAdapter;
    String[] slide_headings;
    String[] slide_descs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);

        slide_headings = new String[]{
                getResources().getString(R.string.theading1),
                getResources().getString(R.string.theading2),
                getResources().getString(R.string.theading3),
                getResources().getString(R.string.theading4),
                getResources().getString(R.string.theading5),
                getResources().getString(R.string.theading6),
                getResources().getString(R.string.theading7),
                getResources().getString(R.string.theading8),


        };

        slide_descs = new String[]{
                getResources().getString(R.string.tcontent1),
                getResources().getString(R.string.tcontent2),
                getResources().getString(R.string.tcontent3),
                getResources().getString(R.string.tcontent4),
                getResources().getString(R.string.tcontent5),
                getResources().getString(R.string.tcontent6),
                getResources().getString(R.string.tcontent7),
                getResources().getString(R.string.tcontent8),


        };


        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mDotLayout = (LinearLayout) findViewById(R.id.dotsLayout);

        slideAdapter = new SlideAdapter(this,slide_headings,slide_descs);

        mViewPager.setAdapter(slideAdapter);

        addDotsIndicator(0);

        mViewPager.addOnPageChangeListener(viewListener);


        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_home:
                        Intent intent = new Intent(Tips.this, SecondActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        break;
                    case R.id.action_bmi:
                        Intent intent2 = new Intent(Tips.this, Bmi.class);
                        startActivity(intent2);
                        break;
                    case R.id.action_calender:
                        Intent intent3 = new Intent(Tips.this, Calender.class);
                        startActivity(intent3);
                        break;
                }
                return true;
            }
        });
    }

    public void addDotsIndicator(int position){
        Dots = new TextView[8];
        mDotLayout.removeAllViews();

        for(int i =0; i < Dots.length;i++){
            Dots [i]= new TextView(this);
            Dots[i].setText(Html.fromHtml("&#8226"));
            Dots[i].setTextSize(40);
            Dots[i].setTextColor(getResources().getColor(R.color.TransparentWhite));

            mDotLayout.addView(Dots[i]);
        }

        if(Dots.length>0){
            Dots[position].setTextColor(getResources().getColor(R.color.splash_background_color));
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int i) {
            addDotsIndicator(i);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
