package com.example.prueba.ui.calcu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.prueba.R;

public class CalcFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.maincalc,container,false);
        ConstraintLayout ConstraintLayout =(ConstraintLayout)view.findViewById(R.id.main);
        return view;
    }
}