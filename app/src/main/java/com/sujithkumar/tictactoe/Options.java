package com.sujithkumar.tictactoe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class Options extends Fragment {

    NavController nav;
    viewmodel rep;
    Button single, dual;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.options, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nav = Navigation.findNavController(view);
        rep = new ViewModelProvider(requireActivity()).get(viewmodel.class);
        single = view.findViewById(R.id.single);
        dual = view.findViewById(R.id.dual);
        single.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rep.setComputer(true);
                nav.navigate(R.id.action_options_to_input);
            }
        });

        dual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rep.setComputer(false);
                nav.navigate(R.id.action_options_to_input);
            }
        });


    }
}
