package com.example.bmi;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

interface MakeVisible {
    void makeVisible();
}

public class MainActivity extends AppCompatActivity implements set_params_fragment.onSomeEventListener {

 private Fragment cuFragment;


//    @Override
//    public void makeVisible() {
//        textView.setVisibility(View.VISIBLE);
//        textViewWeight.setVisibility(View.VISIBLE);
//        seekBar.setVisibility(View.VISIBLE);
//        seekBarWeight.setVisibility(View.VISIBLE);
//        temp1.setVisibility(View.VISIBLE);
//        temp2.setVisibility(View.VISIBLE);
//        btn.setVisibility(View.VISIBLE);
//        temp3.setVisibility(View.VISIBLE);
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cuFragment = set_params_fragment.newInstance("","");
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.set_param_fragment_page,cuFragment,"CALCULATE_TAG")
                .commit();



    }

    @Override
    public void onBackPressed() {

        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count == 0) {
            getSupportFragmentManager().popBackStack();
            finish();
            //super.onBackPressed();
        } else {
            getSupportFragmentManager().beginTransaction().remove(getSupportFragmentManager().findFragmentByTag("RESULT_TAG")).commit();
            getSupportFragmentManager().popBackStack();
        }


    }

    @Override
    public void someEvent(String height, String weight) {
        cuFragment = result_fragment.newInstance(height,weight);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.result_fragment_page,cuFragment,"RESULT_TAG")
                .addToBackStack("RESULT_TAG")
                .commit();
    }
}