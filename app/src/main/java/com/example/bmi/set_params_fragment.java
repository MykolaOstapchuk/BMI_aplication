package com.example.bmi;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.concurrent.CancellationException;


public class set_params_fragment extends Fragment {

    public interface onSomeEventListener{
        public void someEvent(String heigh,String weight);
    }

    onSomeEventListener someEventListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            someEventListener = (onSomeEventListener) context;
        }catch (ClassCastException ex){
            throw new ClassCastException(context.toString() + " must implement onSomeEventListener");
        }
    }

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    SeekBar seek_bar_height;
    SeekBar seek_bar_weight;
    TextView text_view_height;
    TextView text_view_weight;
    TextView calculate_bmi_string,select_height_string,select_weight_string;
    Double height=0.0;
    Integer weight=0;
    Button calculate_btn;



    public static set_params_fragment newInstance(String param1, String param2) {
        set_params_fragment fragment = new set_params_fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
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
        View view =  inflater.inflate(R.layout.fragment_calculate, container,false);

        inicialize(view);



        seek_bar_height.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(i<100) {
                    height = (double)i;
                    text_view_height.setText(i + "cm");
                }
                else {
                    height= (double)i/100 ;
                    text_view_height.setText(height+"m");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });


        seek_bar_weight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                weight=i;
                text_view_weight.setText(i+" kg");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        final Fragment current_fragment = this;

        calculate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //getActivity().getSupportFragmentManager().beginTransaction().remove(current_fragment).commit();
                someEventListener.someEvent(height.toString(),weight.toString());

                //

//                text_view_height.setVisibility(View.GONE);
//                text_view_weight.setVisibility(View.GONE);
//                seek_bar_height.setVisibility(View.GONE);
//                seek_bar_weight.setVisibility(View.GONE);
//                calculate_bmi_string.setVisibility(View.GONE);
//                select_height_string.setVisibility(View.GONE);
//                select_weight_string.setVisibility(View.GONE);
//                calculate_btn.setVisibility(View.GONE);
//
            }
        });

        return view;
    }


    void inicialize(View view){
        calculate_btn = view.findViewById(R.id.calculateBtn);

        select_weight_string=view.findViewById(R.id.Weight);
        select_height_string=view.findViewById(R.id.Height);
        calculate_bmi_string=view.findViewById(R.id.CalculateYourBMI);

        seek_bar_weight = view.findViewById(R.id.sliderWeight);
        text_view_height= view.findViewById(R.id.setHeight);

        seek_bar_height = view.findViewById(R.id.sliderHeight);
        text_view_weight= view.findViewById(R.id.setWeight);

        seek_bar_height.setProgress(0);
        seek_bar_height.setMax(250);

        seek_bar_weight.setProgress(0);
        seek_bar_weight.setMax(200);
    }
}