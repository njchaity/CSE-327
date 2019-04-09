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

public class Diet extends AppCompatActivity {

    private ViewPager mViewPager;
    private LinearLayout mDotLayout;
    private TextView[] Dots;
    private DietSlideAdapter slideAdapter;
    String[] slide_headings2;
    String[] slide_descs2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet);

        slide_headings2 = new String[]{
                getResources().getString(R.string.heading1),
                getResources().getString(R.string.heading2),
                getResources().getString(R.string.heading3),
                getResources().getString(R.string.heading4),
                getResources().getString(R.string.heading5),
        };
        slide_descs2 = new String[]{
                getResources().getString(R.string.content1),
                getResources().getString(R.string.content2),
                getResources().getString(R.string.content3),
                getResources().getString(R.string.content4),
                getResources().getString(R.string.content5),
        };

        mViewPager = (ViewPager) findViewById(R.id.viewPager2);
        mDotLayout = (LinearLayout) findViewById(R.id.dotsLayout2);

        slideAdapter = new DietSlideAdapter(this,slide_headings2,slide_descs2 );

        mViewPager.setAdapter(slideAdapter);

        addDotsIndicator(0);

        mViewPager.addOnPageChangeListener(viewListener);


        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_home:
                        Intent intent = new Intent(Diet.this, SecondActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        break;
                    case R.id.action_bmi:
                        Intent intent2 = new Intent(Diet.this, Bmi.class);
                        startActivity(intent2);
                        break;
                    case R.id.action_calender:
                        Intent intent3 = new Intent(Diet.this, Calender.class);
                        startActivity(intent3);
                        break;
                }
                return true;
            }
        });
    }

    public void addDotsIndicator(int position){
        Dots = new TextView[5];
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
