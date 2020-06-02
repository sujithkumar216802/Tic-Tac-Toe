package com.sujithkumar.tictactoe;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter extends RecyclerView.Adapter<Adapter.viewholder> {
    String a, b;
    Integer c, d;

    Adapter(String name1, String name2, Integer score1, Integer score2) {
        a = name1;
        b = name2;
        c = score1;
        d = score2;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.template, parent, false);
        viewholder temp = new viewholder(v);
        return temp;
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        if (position == 0) {
            holder.name.setText(a+"");
            holder.score.setText(c+"");
        } else {
            holder.name.setText(b+"");
            holder.score.setText(d+"");
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public static class viewholder extends RecyclerView.ViewHolder {
        TextView name, score;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            score = itemView.findViewById(R.id.score);
        }
    }
}
