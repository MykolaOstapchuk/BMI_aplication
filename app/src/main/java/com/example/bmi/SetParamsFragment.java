package com.example.bmi;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import static com.example.bmi.Constants.START_VALUE;


public class SetParamsFragment extends Fragment {

    interface onSomeEventListener {
        void someEvent(Double height, int weight);
    }

    public SetParamsFragment() {
    }

    private onSomeEventListener someEventListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            someEventListener = (onSomeEventListener) context;
        } catch (ClassCastException ex) {
            throw new ClassCastException(context.toString() + " must implement onSomeEventListener");
        }
    }

    private SeekBar seek_bar_height;
    private SeekBar seek_bar_weight;
    private TextView text_view_height;
    private TextView text_view_weight;
    private Double height=0.0;
    private int weight=0;
    private Button calculate_btn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_set_params_fragment, container, false);

        initialise(view);

        seek_bar_height.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (i < 100) {
                    height = (double) i;
                    text_view_height.setText(i + "cm");
                } else {
                    height = (double) i / 100;
                    text_view_height.setText(height + "m");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        seek_bar_weight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                weight = i;
                text_view_weight.setText(i + " kg");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        calculate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                someEventListener.someEvent(height, weight);
            }
        });

        return view;
    }

    void initialise(View view) {
        calculate_btn = view.findViewById(R.id.calculateBtn);

        seek_bar_weight = view.findViewById(R.id.sliderWeight);
        text_view_height = view.findViewById(R.id.setHeight);

        seek_bar_height = view.findViewById(R.id.sliderHeight);
        text_view_weight = view.findViewById(R.id.setWeight);

        seek_bar_height.setProgress(0);
        seek_bar_height.setMax(250);

        seek_bar_weight.setProgress(0);
        seek_bar_weight.setMax(200);
    }
}