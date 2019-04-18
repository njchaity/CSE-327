package helpmom;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class Calender extends AppCompatActivity {
    private EditText mDisplayDate2;
    private TextView mDisplayDate3;
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private TextView calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender2);
        mDisplayDate = (TextView) findViewById(R.id.pView);
        mDisplayDate2 =(EditText) findViewById(R.id.choose);
        mDisplayDate3 = (TextView) findViewById(R.id.dueMsg);



        mDisplayDate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);






                DatePickerDialog dialog = new DatePickerDialog(Calender.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,mDateSetListener,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });






        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_home:
                        Intent intent = new Intent(Calender.this, SecondActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        break;
                    case R.id.action_bmi:
                        Intent intent2 = new Intent(Calender.this, Bmi.class);
                        startActivity(intent2);
                        break;
                    case R.id.action_calender:
                        Intent intent3 = new Intent(Calender.this, Calender.class);
                        startActivity(intent3);
                        break;
                }
                return true;
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month++;
                String date2 = day + "/" + month + "/" + year;
                mDisplayDate2.setText(date2);

                Calendar c = Calendar.getInstance();

                c.set(year, month, day);
                c.add(Calendar.WEEK_OF_YEAR, 40);
                c.add(Calendar.MONTH,-1);
                Date date = c.getTime();



                SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
                String formattedDate = sdf.format(date);
                mDisplayDate3.setText(getString(R.string.msg_date));
                mDisplayDate.setText(formattedDate);

            }
        };

    }
    public void onAddEventClicked(View view){
        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setType("vnd.android.cursor.item/event");

        Calendar cal = Calendar.getInstance();
        long startTime = cal.getTimeInMillis();
        long endTime = cal.getTimeInMillis()  + 60 * 60 * 1000;

        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startTime);
        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,endTime);
        intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);





        startActivity(intent);
    }

}
