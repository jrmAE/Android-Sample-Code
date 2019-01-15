package com.example.jrm533.it214_sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class AnotherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);

        BlankFragment.addFragment(getSupportFragmentManager());
    }

    public void goHome(View view) {
        String rating = getIntent().getStringExtra("OtherPageItem");

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("NextPageItem", "SomeValueHere");
        intent.putExtra("OtherPageItem", rating);

        startActivity(intent);
    }
}
