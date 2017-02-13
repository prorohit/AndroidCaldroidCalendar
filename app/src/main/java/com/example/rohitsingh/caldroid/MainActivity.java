package com.example.rohitsingh.caldroid;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private SimpleDateFormat mDateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = (TextView) findViewById(R.id.textview);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CaldroidFragment dialogCaldroidFragment = CaldroidFragment.newInstance("Select a date", 2, 2017);
                dialogCaldroidFragment.show(getSupportFragmentManager(),"TAG");
            }
        });


        mDateFormatter = new SimpleDateFormat();

        CaldroidFragment caldroidFragment = new CustomGridFragment();
        Bundle args = new Bundle();
        // Setting Up the Customized Theme
        args.putInt(CaldroidFragment.THEME_RESOURCE, R.style.CustomizedThemeForArrows);
        Calendar cal = Calendar.getInstance();
        args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
        args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
        caldroidFragment.setArguments(args);



        final CaldroidListener listener = new CaldroidListener() {

            @Override
            public void onSelectDate(Date date, View view) {
                Toast.makeText(getApplicationContext(), mDateFormatter.format(date),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onChangeMonth(int month, int year) {
                String text = "month: " + month + " year: " + year;
                Toast.makeText(getApplicationContext(), text,
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClickDate(Date date, View view) {
                Toast.makeText(getApplicationContext(),
                        "Long click " + mDateFormatter.format(date),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCaldroidViewCreated() {
                Toast.makeText(getApplicationContext(),
                        "Caldroid view is created",
                        Toast.LENGTH_SHORT).show();
            }

        };


        caldroidFragment.setCaldroidListener(listener);
        caldroidFragment.setSelectedDate(Calendar.getInstance().getTime());
        caldroidFragment.setTextColorForDate(R.color.colorAccent, Calendar.getInstance().getTime());
        caldroidFragment.refreshView();


        ColorDrawable green = new ColorDrawable(Color.GREEN);
        caldroidFragment.setBackgroundDrawableForDate(green, Calendar.getInstance().getTime());


        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.calendar1, caldroidFragment);
        t.commit();

    }
}
