package com.example.bmi;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Calculate#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Calculate extends Fragment {

    Boolean c1,c2,c3;
    Button returnBtn;
    TextView Result;
    TextView ResultInfo;
    FrameLayout frameLayout;
    int r=0;
    float t=0;


    // TODO: Rename and change types and number of parameters
    public static Calculate newInstance(String param1, String param2) {
        Calculate fragment = new Calculate();
        Bundle args = new Bundle();
        args.putString("a",param1);
        args.putString("b",param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        c1=c2=c3=false;
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_calculate, container, false);

        returnBtn = view.findViewById(R.id.returnBtn);
        Result      = view.findViewById(R.id.Result);
        ResultInfo  = view.findViewById(R.id.ResultInfo);
        //frameLayout = view.findViewById(R.id.BMIback);

        //frameLayout.setBackgroundResource(R.drawable.test);

        t = Float.parseFloat(getArguments().getString("a"));
        r = Integer.parseInt(getArguments().getString("b"));




        if(r/(t*t) < 18.5){
            ResultInfo.setText("Eat more pies!");
            //frameLayout.setBackgroundColor(0xff00ff00);
        }
        else if(r/(t*t)< 24.9){
            //c2=true;
            ResultInfo.setText("Fit as a fiddle!");
            //frameLayout.setBackgroundColor(0x00ccff);
        }
        else {
            //c3=true;
            ResultInfo.setText("Eat less pies!");
            //frameLayout.setBackgroundColor(0xff00ff);
        }

        if(r!=0 && t!=0)
        Result.setText(String.format("%.2f",r/(t*t)));
        else
            Result.setText(String.valueOf(0.0));

        final Fragment t = this;


        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MakeVisible makeVisible = (MakeVisible) getActivity();
                makeVisible.makeVisible();
               getActivity().getSupportFragmentManager().beginTransaction().remove(t).commit();
            }
        });

        return view;
    }


//    @Override
//    public void onDetach() {
//
//        MakeVisible makeVisible = (MakeVisible) getActivity();
//        makeVisible.makeVisible();
//
//        //getActivity().onBackPressed();
//
//        super.onDetach();
//    }
}