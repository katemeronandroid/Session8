package com.firstexample.emarkova.session8;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Loader<Integer> loaderColor;
    private Loader<List<ItemEntity>> loaderItems;
    private MyAsyncTask myAsyncTask;
    private Fragment3 fragment3;
    private MyFragmentCallbacks<Integer> colorFragmentCallback;
    private MyFragmentCallbacks<List<ItemEntity>> itemFragmentCallbacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myAsyncTask = (MyAsyncTask) getLastCustomNonConfigurationInstance();
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        myAsyncTask.unlink();
        return myAsyncTask;
    }

    @Override
    protected void onStart() {
        super.onStart();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment1 fragment1 = new Fragment1();
        Fragment2 fragment2 = new Fragment2();
        fragment3 = new Fragment3();
        fragmentTransaction.add(R.id.frame1,fragment1);
        fragmentTransaction.add(R.id.frame2,fragment2);
        fragmentTransaction.add(R.id.frame3,fragment3);
        fragmentTransaction.commit();
        loaderColor = getSupportLoaderManager().initLoader(MyLoaderCallback.LOADER_ID, null, new MyLoaderCallback());
        loaderItems = getSupportLoaderManager().initLoader(RecyclerLoaderCallback.LOADER_ID, null, new RecyclerLoaderCallback());
        colorFragmentCallback = fragment1;
        itemFragmentCallbacks = fragment2;
        loaderColor.forceLoad();
        loaderItems.forceLoad();
        if(myAsyncTask == null) {
            myAsyncTask = new MyAsyncTask();
            myAsyncTask.execute();
        }
        myAsyncTask.link(this);
    }

    interface MyFragmentCallbacks<T> {
        void onReceive(T data);
    }

    private class MyLoaderCallback implements LoaderManager.LoaderCallbacks<Integer> {

        static final int LOADER_ID = 1000;

        @NonNull
        @Override
        public Loader<Integer> onCreateLoader(int i, @Nullable Bundle bundle) {
            if(i == LOADER_ID)
                return new MyLoader(MainActivity.this);
            else
                return null;
        }

        @Override
        public void onLoadFinished(@NonNull Loader<Integer> loader, Integer integer) {
            colorFragmentCallback.onReceive(integer);
            loaderColor.forceLoad();
        }

        @Override
        public void onLoaderReset(@NonNull Loader<Integer> loader) {

        }
    }

    private class RecyclerLoaderCallback implements LoaderManager.LoaderCallbacks<List<ItemEntity>> {
        static final int LOADER_ID = 2000;

        @NonNull
        @Override
        public Loader<List<ItemEntity>> onCreateLoader(int i, @Nullable Bundle bundle) {
            if(i ==LOADER_ID)
                return new RecyclerLoader(MainActivity.this);
            return null;
        }

        @Override
        public void onLoadFinished(@NonNull Loader<List<ItemEntity>> loader, List<ItemEntity> itemEntities) {
            itemFragmentCallbacks.onReceive(itemEntities);
            loaderItems.forceLoad();
        }

        @Override
        public void onLoaderReset(@NonNull Loader<List<ItemEntity>> loader) {

        }
    }

    public Fragment3 getFragment3() {
        return fragment3;
    }
}

