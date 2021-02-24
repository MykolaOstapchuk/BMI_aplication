package com.example.bmi;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

class Constants{
    public static String RESULT_TAG="RESULT_TAG";
    public static String CALCULATE_TAG="CALCULATE_TAG";
}


public class MainActivity extends AppCompatActivity implements SetParamsFragment.onSomeEventListener {

    private Fragment cuFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cuFragment = SetParamsFragment.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.set_param_fragment_page, cuFragment, Constants.CALCULATE_TAG)
                .commit();
    }

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count == 0) {
            getSupportFragmentManager().popBackStack();
            finish();
        } else {
            getSupportFragmentManager().beginTransaction().remove(getSupportFragmentManager().findFragmentByTag(Constants.RESULT_TAG)).commit();
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public void someEvent(Double height, int weight) {
        cuFragment = ResultFragment.newInstance(height, weight);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.result_fragment_page, cuFragment, Constants.RESULT_TAG)
                .addToBackStack(Constants.RESULT_TAG)
                .commit();
    }
}