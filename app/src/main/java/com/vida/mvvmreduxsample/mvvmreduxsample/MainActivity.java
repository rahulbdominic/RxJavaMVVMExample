package com.vida.mvvmreduxsample.mvvmreduxsample;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity
        extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TodoFragment fragment = TodoFragment.newInstance();

        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .add(R.id.container, fragment)
                .commit();

        TodoViewModel vm = new TodoViewModel();
        vm.subscribeActual(fragment);
    }
}
