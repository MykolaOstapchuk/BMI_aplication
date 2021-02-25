package com.example.bmi;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import static com.example.bmi.Constants.CALCULATE_TAG;
import static com.example.bmi.Constants.RESULT_TAG;


public class MainActivity extends AppCompatActivity implements SetParamsFragment.onSomeEventListener {

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
            finish();
            super.onBackPressed();
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public void someEvent(Double height, int weight) {
        cuFragment = new ResultFragment(height, weight);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_page, cuFragment, RESULT_TAG)
                .addToBackStack(RESULT_TAG)
                .commit();
    }
}