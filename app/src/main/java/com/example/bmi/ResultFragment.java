package com.example.bmi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;


import androidx.fragment.app.Fragment;


public class ResultFragment extends Fragment {

    public static int START_VALUE=0;
    public static String HEIGHT="height";
    public static String WEIGHT="weight";
    public static String MOREPIES ="Eat more pies!";
    public static String FIDDLE="Fit as a fiddle!";
    public static String LESSPIES="Eat less pies!";

    Button returnBtn;
    TextView Result;
    TextView ResultInfo;
    int weight=START_VALUE;
    double height=START_VALUE;
    FrameLayout frameLayout;

    public static ResultFragment newInstance(Double param1, int param2) {
        ResultFragment fragment = new ResultFragment();
        Bundle args = new Bundle();
        args.putDouble(HEIGHT, param1);
        args.putInt(WEIGHT, param2);
        fragment.setArguments(args);
        return fragment;
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

        height = getArguments().getDouble(HEIGHT);
        weight = getArguments().getInt(WEIGHT);

        if (weight / (height * height) < 18.5) {
            ResultInfo.setText(MOREPIES);
            frameLayout.setBackgroundColor(getResources().getColor(R.color.colorGreen));
        } else if (weight / (height * height) < 24.9) {
            ResultInfo.setText(FIDDLE);
            frameLayout.setBackgroundColor(getResources().getColor(R.color.colorSky));
        } else {
            ResultInfo.setText(LESSPIES);
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
                getActivity().getSupportFragmentManager().beginTransaction().remove(t).commit();
            }
        });

        return view;
    }
}