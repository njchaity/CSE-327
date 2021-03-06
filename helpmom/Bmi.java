package helpmom;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Bmi extends AppCompatActivity {
    private EditText feet;
    private EditText inch;
    private EditText weight;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);
        feet = (EditText) findViewById(R.id.feet);
        inch = (EditText) findViewById(R.id.inch);
        weight = (EditText) findViewById(R.id.weight);
        result = (TextView) findViewById(R.id.result);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_home:
                        Intent intent = new Intent(Bmi.this, SecondActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        break;
                    case R.id.action_bmi:
                        Intent intent2 = new Intent(Bmi.this, Bmi.class);
                        startActivity(intent2);
                        break;
                    case R.id.action_calender:
                        Intent intent3 = new Intent(Bmi.this, Calender.class);
                        startActivity(intent3);
                        break;
                }
                return true;
            }
        });
    }

    public void calculateBMI(View v) {
        String feets = feet.getText().toString();
        String inchs = inch.getText().toString();

        int value1 = Integer.parseInt(feets);
        int value2 = Integer.parseInt(inchs);

        double height = (value1*30.48)+ (value2*2.54);


        String weightStr = weight.getText().toString();

        if (feets != null && !"".equals(height) && inchs != null && !"".equals(height)
                && weightStr != null  &&  !"".equals(weightStr)) {
            double heightValue = height / 100;
            double weightValue = Double.parseDouble(weightStr);

            double bmi = weightValue / (heightValue * heightValue);

            displayBMI(bmi);
        }
    }

    private void displayBMI(Double bmi) {
        String bmiLabel = "";

        if (Double.compare(bmi, 15f) <= 0) {
            bmiLabel = getString(R.string.very_severely_underweight);
        } else if (Double.compare(bmi, 15f) > 0  &&  Double.compare(bmi, 16f) <= 0) {
            bmiLabel = getString(R.string.severely_underweight);
        } else if (Double.compare(bmi, 16f) > 0  && Double.compare(bmi, 18.5f) <= 0) {
            bmiLabel = getString(R.string.underweight);
        } else if (Double.compare(bmi, 18.5f) > 0  &&  Double.compare(bmi, 25f) <= 0) {
            bmiLabel = getString(R.string.normal);
        } else if (Double.compare(bmi, 25f) > 0  &&  Double.compare(bmi, 30f) <= 0) {
            bmiLabel = getString(R.string.overweight);
        } else if (Double.compare(bmi, 30f) > 0  &&  Double.compare(bmi, 35f) <= 0) {
            bmiLabel = getString(R.string.obese_class_i);
        } else if (Double.compare(bmi, 35f) > 0  &&  Double.compare(bmi, 40f) <= 0) {
            bmiLabel = getString(R.string.obese_class_ii);
        } else {
            bmiLabel = getString(R.string.obese_class_iii);
        }
        double round = Math.round(bmi * 100.0)/100.0;
        bmiLabel = getString(R.string.your_bmi) + round + "\n\n" + bmiLabel;
        result.setText(bmiLabel);
    }
}