package com.firstexample.emarkova.session8;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import java.util.Random;

public class MyLoader extends AsyncTaskLoader<Integer> {
    private static final int TIMEOUT = 1000;

    public MyLoader(@NonNull Context context) {
        super(context);
    }

    @Nullable
    @Override
    public Integer loadInBackground() {
        try {
            Thread.sleep(TIMEOUT);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return returnColor();
    }

    private Integer returnColor() {
        Random random = new Random();
        return Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }
}
