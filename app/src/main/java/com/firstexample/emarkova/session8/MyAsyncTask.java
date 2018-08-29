package com.firstexample.emarkova.session8;

import android.os.AsyncTask;
import android.support.v4.app.LoaderManager;
import android.util.Log;
import android.widget.TextView;

import java.util.Random;

public class MyAsyncTask extends AsyncTask<Void, Void, Integer> {
    private static final int TIMEOUT = 1000;
    MainActivity activity;

    public void link(MainActivity activity){
        this.activity = activity;
    }

    public void unlink() {
        activity = null;
    }

    @Override
    protected Integer doInBackground(Void... voids) {
        try {
            Thread.sleep(TIMEOUT);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return returnInteger();
    }

    @Override
    protected void onPostExecute(Integer integer) {
        TextView textView = activity.getFragment3().getTextView().findViewById(R.id.textView);
        textView.setText(integer.toString());
        MyAsyncTask task = new MyAsyncTask();
        task.link(activity);
        task.execute();
        unlink();
    }

    private Integer returnInteger() {
        Random random = new Random();
        return random.nextInt(100);
    }
}
