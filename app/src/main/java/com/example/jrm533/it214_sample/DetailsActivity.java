package com.example.jrm533.it214_sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
    }

    public void goHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("NextPageItem", "SomeValueHere");
        startActivity(intent);
    }
}
