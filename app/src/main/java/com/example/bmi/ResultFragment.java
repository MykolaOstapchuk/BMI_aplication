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

    private int weight;
    private double height;

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

        Button returnBtn = view.findViewById(R.id.returnBtn);
        TextView result = view.findViewById(R.id.Result);
        TextView resultInfo = view.findViewById(R.id.ResultInfo);
        FrameLayout frameLayout = view.findViewById(R.id.resultBackground);

        if(weight == 0 || height == 0){
            result.setText(String.valueOf(0.0));
        }
        else{
            if (weight / (height * height) < 18.5) {
                resultInfo.setText(R.string.EatMorePies);
                frameLayout.setBackgroundColor(getResources().getColor(R.color.colorGreen));
            } else if (weight / (height * height) < 24.9) {
                resultInfo.setText(R.string.FitAsFiddle);
                frameLayout.setBackgroundColor(getResources().getColor(R.color.colorSky));
            } else {
                resultInfo.setText(R.string.EatLessPies);
                frameLayout.setBackgroundColor(getResources().getColor(R.color.colorPink));
            }
            result.setText(String.format("%.2f", weight / (height * height)));
        }

        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        return view;
    }
}