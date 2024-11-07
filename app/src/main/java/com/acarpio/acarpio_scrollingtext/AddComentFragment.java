package com.acarpio.acarpio_scrollingtext;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class AddComentFragment extends Fragment {

    private EditText textoComentario;
    private EditText nombreUsuario;
    private EditText tituloComentario;
    private TextView charContador;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_comment, container, false);

        // Finding views
        textoComentario = view.findViewById(R.id.frAddComment);
        nombreUsuario = view.findViewById(R.id.frUserName);
        tituloComentario = view.findViewById(R.id.frTitle);
        charContador = view.findViewById(R.id.charContador);


        // Listener

        CharCounter counter = new CharCounter(charContador, 200);
        textoComentario.addTextChangedListener(counter);

        nombreUsuario.requestFocus();


        return view;

    }

    // Getting the text
    public String getTextoComentario() {
        return textoComentario.getText().toString();
    }

    public String getNombreUsuario() {
        return nombreUsuario.getText().toString();
    }

    public String getTituloComentario() {
        return tituloComentario.getText().toString();
    }
}
