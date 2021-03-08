package com.example.bmi;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import static com.example.bmi.Constants.CALCULATE_TAG;
import static com.example.bmi.Constants.RESULT_TAG;


public class MainActivity extends AppCompatActivity implements SetParamsFragment.onSetParamListener {

    private Fragment cuFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cuFragment = new SetParamsFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_page, cuFragment, CALCULATE_TAG)
                .commit();
    }

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count == 0) {
            super.onBackPressed();
            finish();
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public void setParam(double height, int weight) {
        cuFragment = new ResultFragment(height, weight);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_page, cuFragment, RESULT_TAG)
                .addToBackStack(RESULT_TAG)
                .commit();
    }
}