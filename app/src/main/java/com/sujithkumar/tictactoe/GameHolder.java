package com.sujithkumar.tictactoe;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class GameHolder extends Fragment {
    static AI objj = new AI();
    MediaPlayer x, o, d, single, multi;
    Button next;
    Observer<Boolean> over;
    private TextView player1, player2, round;
    private Game obj;
    private NavController nav;
    private viewmodel rep;
    private Observer<Integer> one, two, current;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.gameholder, container, false);
    }


    @Override
    public void onPause() {
        super.onPause();
        /*x.pause();
        x.seekTo(0);
        o.pause();
        o.seekTo(0);
        d.pause();
        d.seekTo(0);
        single.pause();
        multi.pause();*/
        x.release();
        o.release();
        d.release();
        single.release();
        multi.release();

    }

    @Override
    public void onResume() {
        super.onResume();
        x = MediaPlayer.create(requireContext(), R.raw.xwin);
        o = MediaPlayer.create(requireContext(), R.raw.owin);
        d = MediaPlayer.create(requireContext(), R.raw.draw);
        single = MediaPlayer.create(requireContext(), R.raw.single);
        multi = MediaPlayer.create(requireContext(), R.raw.dual);

        if (!rep.getCurrentroundover().getValue()) {
            if (rep.isComputer()) {

                single.start();
                single.setLooping(true);
            } else {

                multi.setLooping(true);
                multi.start();
            }

        }

        if (rep.isComputer()) {
            rep.setName2("Computer");
        }
        player1.setText(rep.getName1() + "\n" + rep.getScore1().getValue());
        player2.setText(rep.getName2() + "\n" + rep.getScore2().getValue());
        round.setText("ROUND :\n" + rep.getCurrentround() + "/" + rep.getRound());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rep = new ViewModelProvider(requireActivity()).get(viewmodel.class);
        nav = Navigation.findNavController(view);
        player1 = view.findViewById(R.id.firstplayer);
        player2 = view.findViewById(R.id.secondplayer);
        round = view.findViewById(R.id.round);
        next = view.findViewById(R.id.next);
        obj = new Game(requireContext());
        over = new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    if (rep.isComputer() && single!=null)
                        single.pause();
                    else if(multi!=null)
                        multi.pause();
                    next.setVisibility(View.VISIBLE);
                    switch (rep.getWinner()) {
                        case 0:
                            if(d!=null)
                            d.start();
                            Log.i("winner", "d");
                            break;
                        case 1:
                            if(x!=null)
                            x.start();
                            Log.i("winner", "x");
                            break;
                        case 2:
                            if(o!=null)
                            o.start();
                            Log.i("winner", "o");
                            break;
                    }
                }
            }
        };
        rep.getCurrentroundover().observe(requireActivity(), over);
        one = new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                player1.setText(rep.getName1() + "\n" + rep.getScore1().getValue());
            }
        };
        rep.getScore1().observe(requireActivity(), one);
        two = new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                player2.setText(rep.getName2() + "\n" + rep.getScore2().getValue());
                if (rep.isComputer())
                    player2.setText("Computer \n" + rep.getScore2().getValue());
            }
        };
        rep.getScore2().observe(requireActivity(), two);

        current = new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {

                if (integer == 1) {
                    player1.setBackgroundColor(Color.argb(80, 0, 255, 255));
                    player2.setBackgroundColor(Color.TRANSPARENT);
                }

                if (integer == 2) {
                    player2.setBackgroundColor(Color.argb(80, 255, 255, 0));
                    player1.setBackgroundColor(Color.TRANSPARENT);
                }


                if (!rep.getCurrentroundover().getValue() && integer == 2 && rep.isComputer()) {
                    int[] xy = objj.value(rep.getGrid());
                    rep.setgrid(xy[1], xy[0], 2);
                    rep.checkwinner();
                    rep.getCurrent().setValue(1);
                }
            }
        };
        rep.getCurrent().observe(requireActivity(), current);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(x.isPlaying()){
                    x.pause();
                    x.seekTo(0);
                }
                if(o.isPlaying()){

                    o.pause();
                    o.seekTo(0);
                }
                if(d.isPlaying()){

                    d.pause();
                    d.seekTo(0);
                }
                if (rep.getCurrentround() == rep.getRound()) {

                    nav.navigate(R.id.action_gameHolder_to_scoreBoard);
                }
                if (rep.isComputer() && single!=null)
                    single.start();
                else if(multi!=null)
                    multi.start();
                rep.setCurrentround(rep.getCurrentround() + 1);
                rep.getCurrentroundover().setValue(false);
                next.setVisibility(View.GONE);
                rep.cleargrid();
                round.setText("ROUND :\n" + rep.getCurrentround() + "/" + rep.getRound());
            }
        });
        next.setVisibility(View.GONE);
    }
}