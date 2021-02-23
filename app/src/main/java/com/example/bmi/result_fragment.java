package com.example.bmi;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;


public class result_fragment extends Fragment {

    Button returnBtn;
    TextView Result;
    TextView ResultInfo;
    int weight=0;
    float height=0;
    FrameLayout frameLayout;

    public static result_fragment newInstance(String param1, String param2) {
        result_fragment fragment = new result_fragment();
        Bundle args = new Bundle();
        args.putString("a",param1);
        args.putString("b",param2);
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

        View view =  inflater.inflate(R.layout.fragment_result_fragment, container, false);

        returnBtn = view.findViewById(R.id.returnBtn);
        Result      = view.findViewById(R.id.Result);
        ResultInfo  = view.findViewById(R.id.ResultInfo);
        frameLayout = view.findViewById(R.id.resultBackground);


        height = Float.parseFloat(getArguments().getString("a"));
        weight = Integer.parseInt(getArguments().getString("b"));


        if(weight/(height*height) < 18.5){
            ResultInfo.setText("Eat more pies!");
            frameLayout.setBackgroundColor(getResources().getColor(R.color.colorGreen));
        }
        else if(weight/(height*height)< 24.9){
            ResultInfo.setText("Fit as a fiddle!");
            frameLayout.setBackgroundColor(getResources().getColor(R.color.colorSky));
        }
        else {
            ResultInfo.setText("Eat less pies!");
            frameLayout.setBackgroundColor(getResources().getColor(R.color.colorPink));
        }

        if(weight!=0 && height!=0)
            Result.setText(String.format("%.2f",weight/(height*height)));
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