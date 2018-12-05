package com.example.jrm533.it214_sample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity
        extends AppCompatActivity implements Comparable{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        List<Comparable> someListOfThings = new ArrayList<>();

        someListOfThings.add(this);



    }

    @Override
    public int compareTo(@NonNull Object o) {
        return 0;
    }






}
