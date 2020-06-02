package com.sujithkumar.tictactoe;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class SplashScreen extends Fragment {

    static boolean over = false, pause = false;
    ImageView logo;
    TextView name;
    TextView by;
    Animation bytext, image, title;
    private NavController nav;

    @Override
    public void onPause() {
        super.onPause();
        pause = true;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.splashscreen, container, false);
    }


    @Override
    public void onResume() {
        super.onResume();
        pause = false;
        if (over)
            nav.navigate(R.id.action_splashScreen_to_options);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nav = Navigation.findNavController(view);
        logo = view.findViewById(R.id.logo);
        name = view.findViewById(R.id.name);
        by = view.findViewById(R.id.by);
        bytext = AnimationUtils.loadAnimation(requireContext(), R.anim.by);
        image = AnimationUtils.loadAnimation(requireContext(), R.anim.logo);
        title = AnimationUtils.loadAnimation(requireContext(), R.anim.name);
        logo.setAnimation(image);
        name.setAnimation(title);
        by.setAnimation(bytext);
        logo.setVisibility(View.INVISIBLE);
        name.setVisibility(View.INVISIBLE);
        //by.setVisibility(View.INVISIBLE);
        new CountDownTimer(2400, 1) {

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                over = true;
                by.setVisibility(View.INVISIBLE);

                if (!pause)
                    nav.navigate(R.id.action_splashScreen_to_options);
            }
        }.start();
    }
}
