package com.sujithkumar.tictactoe;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;


public class Game extends View {
    static Paint x, o, line;
    static long pixeldistance;
    static int pointx, pointy;
    static viewmodel rep;

    public Game(Context context) {
        super(context);
        init(context);
    }

    public Game(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    public Game(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public Game(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }


    void init(Context context) {
        //  obj = new AI();
        rep = new ViewModelProvider((ViewModelStoreOwner) context).get(viewmodel.class);
        x = new Paint();
        o = new Paint();
        line = new Paint();
        line = new Paint();
        x.setColor(Color.RED);
        x.setStrokeWidth(8);
        o.setColor(Color.BLUE);
        o.setStyle(Paint.Style.STROKE);
        o.setStrokeWidth(8);
        line.setColor(Color.BLACK);
        line.setStrokeWidth(15);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        pixeldistance = Math.min(getWidth(), getHeight());
        pixeldistance /= 3;
        //horizontal
        canvas.drawLine(0, pixeldistance, pixeldistance * 3, pixeldistance, line);
        canvas.drawLine(0, pixeldistance * 2, pixeldistance * 3, pixeldistance * 2, line);
        //vertical
        canvas.drawLine(pixeldistance, 0, pixeldistance, pixeldistance * 3, line);
        canvas.drawLine(pixeldistance * 2, 0, pixeldistance * 2, pixeldistance * 3, line);
        //x
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (rep.getgrid(i, j) == 1) {
                    canvas.drawLine(j * pixeldistance + (float) 0.1 * pixeldistance, i * pixeldistance + (float) 0.1 * pixeldistance, (j + 1) * pixeldistance - (float) 0.1 * pixeldistance, (i + 1) * pixeldistance - (float) 0.1 * pixeldistance, x);
                    canvas.drawLine(j * pixeldistance + (float) 0.1 * pixeldistance, (i + 1) * pixeldistance - (float) 0.1 * pixeldistance, (j + 1) * pixeldistance - (float) 0.1 * pixeldistance, i * pixeldistance + (float) 0.1 * pixeldistance, x);
                }
        //o
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (rep.getgrid(i, j) == 2) {
                    canvas.drawCircle(j * pixeldistance + (float) 0.5 * pixeldistance, i * pixeldistance + (float) 0.5 * pixeldistance, (float) 0.4 * pixeldistance, o);
                }
        if (rep.getCurrentroundover().getValue()) {

            int x1 = 0, x2 = 0, y1 = 0, y2 = 0;
            switch (rep.getDirection()) {
                case 1:
                    x1 = 0;
                    x2 = (int) (3 * pixeldistance);
                    y1 = (int) (rep.getNumber() * pixeldistance + 0.5 * pixeldistance);
                    y2 = y1;
                    break;
                case 2:
                    y1 = 0;
                    y2 = (int) (3 * pixeldistance);
                    x1 = (int) (rep.getNumber() * pixeldistance + 0.5 * pixeldistance);
                    x2 = x1;
                    break;
                case 3:
                    if (rep.getNumber() == 0) {
                        y1 = 0;
                        y2 = (int) (3 * pixeldistance);
                        x1 = 0;
                        x2 = (int) (3 * pixeldistance);
                    } else {
                        y1 = 0;
                        y2 = (int) (3 * pixeldistance);
                        x2 = 0;
                        x1 = (int) (3 * pixeldistance);
                    }
                    break;
            }

            if (rep.getWinner() == 1){
                canvas.drawLine(x1, y1, x2, y2, x);
            }
            else if (rep.getWinner() == 2){
                canvas.drawLine(x1, y1, x2, y2, o);
            }
            postInvalidate();
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean temp = super.onTouchEvent(event);
        if (event.getAction() == MotionEvent.ACTION_DOWN && !rep.getCurrentroundover().getValue() && (!rep.isComputer() || rep.isComputer() && rep.getCurrent().getValue() == 1)) {
            //x-axis
            if (event.getX() > 0 && event.getX() < pixeldistance)
                pointx = 0;
            else if (event.getX() > pixeldistance && event.getX() < pixeldistance * 2)
                pointx = 1;
            else
                pointx = 2;
            //y-axis
            if (event.getY() > 0 && event.getY() < pixeldistance)
                pointy = 0;
            else if (event.getY() > pixeldistance && event.getY() < pixeldistance * 2)
                pointy = 1;
            else
                pointy = 2;
            //changing the point
            if (rep.getgrid(pointy, pointx) == 0) {
                rep.setgrid(pointy, pointx, rep.getCurrent().getValue());
                rep.checkwinner();
                if (rep.getCurrent().getValue() == 2)
                    rep.getCurrent().setValue(1);
                else {
                    Log.i("tag", "mfff");
                    rep.getCurrent().setValue(2);
                }
                postInvalidate();
                return true;
            }
        }

        return temp;
    }
}
