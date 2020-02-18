package com.example.prueba.ui.tools;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.prueba.R;

public class Histor extends Fragment {

    private EditText et_name, et_valor;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.histor, container, false);
        ConstraintLayout ConstraintLayout = (ConstraintLayout) view.findViewById(R.id.main);
        return view;
    }

    private void save() {
        String name = et_name.getText().toString();
        String valor = et_valor.getText().toString();

        SharedPreferences preferencias = this.getActivity().getSharedPreferences("agenda", Context.MODE_PRIVATE);
        SharedPreferences.Editor obj_editor = preferencias.edit();
        obj_editor.putString(name, valor);
        obj_editor.commit();

        Toast.makeText(this.getActivity(), "Number Saved", Toast.LENGTH_SHORT).show();
    }

    private void Search() {
        String name = et_name.getText().toString();

        SharedPreferences preferencias = this.getActivity().getSharedPreferences("agenda", Context.MODE_PRIVATE);
        String valor = preferencias.getString(name, "");

        if (valor.length() == 0) {
            Toast.makeText(this.getActivity(), "That name doesn't exist", Toast.LENGTH_SHORT).show();

        } else {
            et_valor.setText(valor);
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_lettersave:
                save();
                break;
            case R.id.btn_lettersearch:
                Search();
                break;
        }
    }

}