package com.sujithkumar.tictactoe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ScoreBoard extends Fragment {
    viewmodel rep;
    RecyclerView recycle;
    RecyclerView.LayoutManager layout;
    RecyclerView.Adapter adapter;
    ImageButton reload;
    Button menu;
    NavController nav;
    TextView result;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.scoreboard, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nav = Navigation.findNavController(view);
        rep = new ViewModelProvider(requireActivity()).get(viewmodel.class);
        result = view.findViewById(R.id.result);
        recycle = view.findViewById(R.id.recycler);
        menu = view.findViewById(R.id.menu);
        reload = view.findViewById(R.id.reload);
        if (rep.getScore1().getValue() > rep.getScore2().getValue()) {
            result.setText(getString(R.string.won, rep.getName1()));
        } else if (rep.getScore1().getValue() < rep.getScore2().getValue()) {
            result.setText(getString(R.string.won, rep.getName2()));
        } else {
            result.setText(getString(R.string.tie));
        }
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nav.navigate(R.id.action_scoreBoard_to_options);
            }
        });
        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nav.navigate(R.id.action_scoreBoard_to_input);
            }
        });
        layout = new LinearLayoutManager(requireActivity());
        adapter = new Adapter(rep.getName1(), rep.getName2(), rep.getScore1().getValue(), rep.getScore2().getValue());
        recycle.setAdapter(adapter);
        recycle.setLayoutManager(layout);
    }
}
