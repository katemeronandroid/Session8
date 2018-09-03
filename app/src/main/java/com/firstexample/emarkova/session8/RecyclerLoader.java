package com.firstexample.emarkova.session8;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RecyclerLoader extends AsyncTaskLoader<List<ItemEntity>> {
    private static final int TIMEOUT = 1000;

    public RecyclerLoader(@NonNull Context context) {
        super(context);
    }

    @Nullable
    @Override
    public List<ItemEntity> loadInBackground() {
        try {
            Thread.sleep(TIMEOUT);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return getListOfItems();
    }

    private List<ItemEntity> getListOfItems() {
        List<ItemEntity> result = new ArrayList<ItemEntity>();
        Random random = new Random();
        int MAX = random.nextInt(5);
        for(int i = 0; i < MAX; i++) {
            result.add(new ItemEntity(i));
        }
        return result;
    }
}
