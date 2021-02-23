package com.example.bmi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

interface MakeVisible {
    void makeVisible();
}

public class MainActivity extends AppCompatActivity implements MakeVisible {


    @Override
    public void makeVisible() {
        textView.setVisibility(View.VISIBLE);
        textViewWeight.setVisibility(View.VISIBLE);
        seekBar.setVisibility(View.VISIBLE);
        seekBarWeight.setVisibility(View.VISIBLE);
        temp1.setVisibility(View.VISIBLE);
        temp2.setVisibility(View.VISIBLE);
        btn.setVisibility(View.VISIBLE);
        temp3.setVisibility(View.VISIBLE);
    }

    SeekBar seekBar;
        TextView textView;
        Double temp=0.0;
        SeekBar seekBarWeight;
        TextView textViewWeight;
        TextView temp1,temp2,temp3;
        Double height=0.0;
        Integer weight=0;
        Button btn;
        RelativeLayout relativeLayout;

        Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.calculateBtn);


        temp1=findViewById(R.id.Weight);
        temp2=findViewById(R.id.Height);
        temp3=findViewById(R.id.CalculateYourBMI);

        seekBar = findViewById(R.id.slider);
        textView= findViewById(R.id.setHeight);

        seekBarWeight = findViewById(R.id.sliderHeight);
        textViewWeight= findViewById(R.id.setWeight);

        seekBar.setProgress(0);
        seekBar.setMax(250);

        seekBarWeight.setProgress(0);
        seekBarWeight.setMax(200);



        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(i<100) {
                    height = (double)i;
                    textView.setText(i + "cm");
                }
                else {
                    temp= (double)i/100 ;
                    height=temp;
                    textView.setText(temp+"m");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        seekBarWeight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    weight=i;
                    textViewWeight.setText(i+" kg");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                textView.setVisibility(View.GONE);
                textViewWeight.setVisibility(View.GONE);
                seekBar.setVisibility(View.GONE);
                seekBarWeight.setVisibility(View.GONE);
                temp1.setVisibility(View.GONE);
                temp2.setVisibility(View.GONE);
                temp3.setVisibility(View.GONE);
                btn.setVisibility(View.GONE);



                //back stack?
                currentFragment = Calculate.newInstance(String.valueOf(height),String.valueOf(weight));
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.frameLayout, currentFragment, "LOGIN_TAG")
                        .commit();
            }
        });
    }
}