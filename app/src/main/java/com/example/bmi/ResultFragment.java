package com.example.bmi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import static com.example.bmi.Constants.HEIGHT;
import static com.example.bmi.Constants.START_VALUE;
import static com.example.bmi.Constants.WEIGHT;

public class ResultFragment extends Fragment {

    Button returnBtn;
    TextView Result;
    TextView ResultInfo;
    int weight = START_VALUE;
    double height = START_VALUE;
    FrameLayout frameLayout;

    public ResultFragment(double height, int weight) {
        this.weight = weight;
        this.height = height;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_result_fragment, container, false);

        returnBtn = view.findViewById(R.id.returnBtn);
        Result = view.findViewById(R.id.Result);
        ResultInfo = view.findViewById(R.id.ResultInfo);
        frameLayout = view.findViewById(R.id.resultBackground);

        if (weight / (height * height) < 18.5) {
            ResultInfo.setText(R.string.EatMorePies);
            frameLayout.setBackgroundColor(getResources().getColor(R.color.colorGreen));
        } else if (weight / (height * height) < 24.9) {
            ResultInfo.setText(R.string.FitAsFiddle);
            frameLayout.setBackgroundColor(getResources().getColor(R.color.colorSky));
        } else {
            ResultInfo.setText(R.string.EatLessPies);
            frameLayout.setBackgroundColor(getResources().getColor(R.color.colorPink));
        }

        if (weight != START_VALUE && height != START_VALUE)
            Result.setText(String.format("%.2f", weight / (height * height)));
        else
            Result.setText(String.valueOf(0.0));

        final Fragment t = this;
        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        return view;
    }
}